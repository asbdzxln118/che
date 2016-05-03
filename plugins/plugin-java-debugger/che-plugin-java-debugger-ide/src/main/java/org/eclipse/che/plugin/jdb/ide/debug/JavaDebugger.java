/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.plugin.jdb.ide.debug;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import org.eclipse.che.api.debugger.gwt.client.DebuggerServiceClient;
import org.eclipse.che.api.debugger.shared.dto.Location;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.app.CurrentProject;
import org.eclipse.che.ide.api.editor.EditorAgent;
import org.eclipse.che.ide.api.filetypes.FileTypeRegistry;
import org.eclipse.che.ide.api.project.tree.VirtualFile;
import org.eclipse.che.ide.debug.DebuggerDescriptor;
import org.eclipse.che.ide.debug.DebuggerManager;
import org.eclipse.che.ide.dto.DtoFactory;
import org.eclipse.che.plugin.debugger.ide.debug.AbstractDebugger;
import org.eclipse.che.plugin.debugger.ide.fqn.FqnResolver;
import org.eclipse.che.plugin.debugger.ide.fqn.FqnResolverFactory;
import org.eclipse.che.ide.ext.java.client.projecttree.JavaSourceFolderUtil;
import org.eclipse.che.ide.util.storage.LocalStorageProvider;
import org.eclipse.che.ide.websocket.MessageBusProvider;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.eclipse.che.plugin.jdb.ide.debug.JavaDebugger.ConnectionProperties.HOST;
import static org.eclipse.che.plugin.jdb.ide.debug.JavaDebugger.ConnectionProperties.PORT;


/**
 * The java debugger.
 *
 * @author Anatoliy Bazko
 */
public class JavaDebugger extends AbstractDebugger {

    public static final String ID = "jdb";

    private final AppContext appContext;

    @Inject
    public JavaDebugger(DebuggerServiceClient service,
                        DtoFactory dtoFactory,
                        LocalStorageProvider localStorageProvider,
                        MessageBusProvider messageBusProvider,
                        EventBus eventBus,
                        FqnResolverFactory fqnResolverFactory,
                        JavaDebuggerFileHandler javaDebuggerFileHandler,
                        DebuggerManager debuggerManager,
                        FileTypeRegistry fileTypeRegistry,
                        AppContext appContext,
                        EditorAgent editorAgent) {
        super(service,
              dtoFactory,
              localStorageProvider,
              messageBusProvider,
              eventBus,
              fqnResolverFactory,
              javaDebuggerFileHandler,
              debuggerManager,
              fileTypeRegistry,
              ID,
              editorAgent,
              appContext);
        this.appContext = appContext;
    }

    @Override
    protected List<String> fqnToPath(@NotNull Location location) {
        CurrentProject currentProject = appContext.getCurrentProject();

        if (currentProject == null) {
            return Collections.emptyList();
        }

        String fqn = prepareFQN(location.getTarget());
        String pathSuffix = fqn.replace(".", "/") + ".java";

        List<String> sourceFolders = JavaSourceFolderUtil.getSourceFolders(currentProject);
        List<String> filePaths = new ArrayList<>(sourceFolders.size() + 1);

        for (String sourceFolder : sourceFolders) {
            filePaths.add(sourceFolder + pathSuffix);
        }
        filePaths.add(location.getTarget());

        return filePaths;
    }

    private String prepareFQN(String fqn) {
        //handle fqn in case nested classes
        //handle fqn in case nested classes
        if (fqn.contains("$")) {
            return fqn.substring(0, fqn.indexOf("$"));
        }
        //handle fqn in case lambda expression
        if (fqn.contains("$$")) {
            return fqn.substring(0, fqn.indexOf("$$"));
        }
        return fqn;
    }

    @Override
    protected String pathToFqn(VirtualFile file) {
        List<String> mimeTypes = fileTypeRegistry.getFileTypeByFile(file).getMimeTypes();

        if (!mimeTypes.isEmpty()) {
            String mediaType = mimeTypes.get(0);
            FqnResolver resolver = fqnResolverFactory.getResolver(mediaType);
            if (resolver != null) {
                return resolver.resolveFqn(file);
            }
        }

        return null;
    }

    @Override
    protected DebuggerDescriptor toDescriptor(Map<String, String> connectionProperties) {
        String address = connectionProperties.get(HOST.toString()) + ":" + connectionProperties.get(PORT.toString());
        return new DebuggerDescriptor("", address);
    }

    public enum ConnectionProperties {
        HOST,
        PORT
    }
}
