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

import com.vaadin.shared.ui.ContentMode;

import java.util.Arrays;

import static com.vaadin.ui.themes.ValoTheme.LABEL_BOLD;

/**
 * @author Mawaziny
 */
public class Label extends com.vaadin.ui.Label {

    private static final long serialVersionUID = 8213337003583574373L;

    public Label() {
        super();
    }

    public Label(String value) {
        super(value);
    }

    public Label(String value, ContentMode contentMode) {
        super(value, contentMode);
    }

    public Label withStyle(String... styleNames) {
        Arrays.stream(styleNames).forEach(this::addStyleName);
        return this;
    }

    public Label withBoldStyle() {
        addStyleName(LABEL_BOLD);
        return this;
    }

    public Label withSizeUndefined() {
        setSizeUndefined();
        return this;
    }

    public Label withWidthUndefined() {
        setWidthUndefined();
        return this;
    }
}
