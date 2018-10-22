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

/**
 * @author Mawaziny
 */
public class TextField extends com.vaadin.ui.TextField {
    private static final long serialVersionUID = -4040366491760139848L;

    public TextField() {
        super();
    }

    public TextField(String caption) {
        super(caption);
    }

    public TextField(String caption, boolean required) {
        super(caption);
        setRequiredIndicatorVisible(required);
    }

    public TextField withValue(String value) {
        setValue(value);
        return this;
    }

    public TextField withReadOnly() {
        setReadOnly(true);
        return this;
    }
}
