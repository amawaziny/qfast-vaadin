/*
 * Copyright 2018 QFast
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.qfast.vaadin.ui;

import com.vaadin.server.Resource;

import java.util.Arrays;

/**
 * @author Mawaziny
 */
public class Button extends com.vaadin.ui.Button {
    private static final long serialVersionUID = 28762635209396094L;

    public Button() {
    }

    public Button(String caption) {
        super(caption);
    }

    public Button(Resource icon) {
        super(icon);
    }

    public Button(String caption, Resource icon) {
        super(caption, icon);
    }

    public Button(String caption, Resource icon, ClickListener listener) {
        super(caption, icon);
        addClickListener(listener);
    }

    public Button(String caption, ClickListener listener) {
        super(caption, listener);
    }

    public Button(Resource icon, ClickListener listener) {
        this(icon);
        addClickListener(listener);
    }

    public Button withStyle(String... styleNames) {
        Arrays.stream(styleNames).forEach(this::addStyleName);
        return this;
    }

    public Button withFullWidth() {
        setWidth("100%");
        return this;
    }
}
