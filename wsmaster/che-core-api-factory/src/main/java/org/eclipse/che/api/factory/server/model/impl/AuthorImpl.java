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

import org.eclipse.che.api.factory.shared.model.Author;

import java.util.Objects;

/**
 * Data object for {@link Author}.
 *
 * @author Anton Korneta
 */
public class AuthorImpl implements Author {

    public static AuthorImplBuilder builder() {
        return new AuthorImplBuilder();
    }

    private Long   created;
    private String name;
    private String userId;
    private String email;

    public AuthorImpl(Long created,
                      String name,
                      String userId,
                      String email) {
        this.created = created;
        this.name = name;
        this.userId = userId;
        this.email = email;
    }

    public AuthorImpl(Author creator) {
        this(creator.getCreated(),
             creator.getName(),
             creator.getUserId(),
             creator.getEmail());
    }

    @Override
    public Long getCreated() {
        return created;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AuthorImpl)) return false;
        final AuthorImpl other = (AuthorImpl)obj;
        return Objects.equals(userId, other.userId)
               && Objects.equals(name, other.name)
               && Objects.equals(email, other.email)
               && Objects.equals(created, other.created);
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(userId);
        result = 31 * result + Long.hashCode(created);
        return result;
    }

    @Override
    public String toString() {
        return "AuthorImpl{" +
               "created=" + created +
               ", name='" + name + '\'' +
               ", userId='" + userId + '\'' +
               ", email='" + email + '\'' +
               '}';
    }

    /**
     * Helps to create the instance of {@link AuthorImpl}.
     */
    public static class AuthorImplBuilder {

        private long   created;
        private String name;
        private String userId;
        private String email;

        public AuthorImpl build() {
            return new AuthorImpl(created, name, userId, email);
        }

        private AuthorImplBuilder() {}

        public AuthorImplBuilder setCreated(long created) {
            this.created = created;
            return this;
        }

        public AuthorImplBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AuthorImplBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public AuthorImplBuilder setEmail(String email) {
            this.email = email;
            return this;
        }
    }
}
