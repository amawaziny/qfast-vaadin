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

import static com.vaadin.event.ShortcutAction.KeyCode.ENTER;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;

public class PrimaryButton extends Button {

    private static final long serialVersionUID = -2021035436509034505L;

    {
        setStyleName(BUTTON_PRIMARY);
        setClickShortcut(ENTER);
    }

    public PrimaryButton(String caption) {
        super(caption);
    }

    public PrimaryButton(Resource icon) {
        super(icon);
    }

    public PrimaryButton(String caption, Resource icon) {
        super(caption, icon);
    }

    public PrimaryButton(String caption, Resource icon, ClickListener listener) {
        super(caption, icon);
        addClickListener(listener);
    }

    public PrimaryButton(String caption, ClickListener listener) {
        super(caption, listener);
    }

    public PrimaryButton(Resource icon, ClickListener listener) {
        this(icon);
        addClickListener(listener);
    }
}
