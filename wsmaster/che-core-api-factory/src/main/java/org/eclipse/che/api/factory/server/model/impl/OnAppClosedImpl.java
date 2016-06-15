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
package org.eclipse.che.api.factory.server.model.impl;

import org.eclipse.che.api.factory.shared.model.Action;
import org.eclipse.che.api.factory.shared.model.OnAppClosed;

import java.util.List;

/**
 * @author Anton Korneta
 */
import static java.util.stream.Collectors.toList;

public class OnAppClosedImpl implements OnAppClosed {
    private List<ActionImpl> actions;

    public OnAppClosedImpl(List<? extends Action> actions) {
        if (actions != null) {
            this.actions = actions.stream()
                                  .map(ActionImpl::new)
                                  .collect(toList());
        }
    }

    public OnAppClosedImpl(OnAppClosed onAppClosed) {
        this(onAppClosed.getActions());
    }

    @Override
    public List<ActionImpl> getActions() {
        return actions;
    }

    public void setActions(List<ActionImpl> actions) {
        this.actions = actions;
    }

    public OnAppClosedImpl withActions(List<ActionImpl> actions) {
        this.actions = actions;
        return this;
    }

}
