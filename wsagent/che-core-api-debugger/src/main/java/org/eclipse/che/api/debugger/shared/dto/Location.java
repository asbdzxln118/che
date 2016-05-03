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
package org.eclipse.che.api.debugger.shared.dto;

import org.eclipse.che.dto.shared.DTO;

/** @author andrew00x */
@DTO
public interface Location {
    String getTarget();

    void setTarget(String target);

    Location withTarget(String target);

    int getLineNumber();

    void setLineNumber(int lineNumber);

    Location withLineNumber(int lineNumber);

    //todo need talk about it with Anatoliy
    String getProjectName();

    void setProjectName(String projectName);

    Location withProjectName(String projectName);


    LinePosition getLinePosition();

    void setLinePosition(LinePosition linePosition);

    Location withLinePosition(LinePosition linePosition);
}
