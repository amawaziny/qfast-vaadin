/*
 * Copyright 2017 Sphere-Consulting
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

/**
 * @author Mawaziny
 */
public class FormLayout extends com.vaadin.ui.FormLayout {
    private static final long serialVersionUID = -3248339171457899795L;

    {
        setSpacing(false);
        setMargin(false);
    }

    public FormLayout() {
    }

    public FormLayout withMargin() {
        setMargin(true);
        return this;
    }

    public FormLayout withSizeFull() {
        setSizeFull();
        return this;
    }

    public FormLayout withStyleName(String styleName) {
        addStyleName(styleName);
        return this;
    }
}
