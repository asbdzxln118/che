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

import org.eclipse.che.api.factory.shared.model.ButtonAttributes;

import java.util.Objects;

/**
 * Data object for {@link ButtonAttributes}.
 *
 * @author Anton Korneta
 */
public class ButtonAttributesImpl implements ButtonAttributes {

    private String  color;
    private String  logo;
    private String  style;
    private Boolean counter;

    public ButtonAttributesImpl(String color,
                                String logo,
                                String style,
                                Boolean counter) {
        this.color = color;
        this.logo = logo;
        this.style = style;
        this.counter = counter;
    }

    public ButtonAttributesImpl(ButtonAttributes attributes) {
        this(attributes.getColor(),
             attributes.getLogo(),
             attributes.getStyle(),
             attributes.getCounter());
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    @Override
    public String getStyle() {
        return style;
    }

    @Override
    public Boolean getCounter() {
        return counter;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ButtonAttributesImpl)) return false;
        final ButtonAttributesImpl other = (ButtonAttributesImpl)obj;
        return Objects.equals(color, other.color)
               && Objects.equals(logo, other.logo)
               && Objects.equals(style, other.style)
               && counter == other.counter;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + Objects.hashCode(color);
        result = 31 * result + Objects.hashCode(logo);
        result = 31 * result + Objects.hashCode(style);
        result = 31 * result + Boolean.hashCode(counter);
        return result;
    }

    @Override
    public String toString() {
        return "ButtonAttributesImpl{" +
               "color='" + color + '\'' +
               ", logo='" + logo + '\'' +
               ", style='" + style + '\'' +
               ", counter=" + counter +
               '}';
    }
}
