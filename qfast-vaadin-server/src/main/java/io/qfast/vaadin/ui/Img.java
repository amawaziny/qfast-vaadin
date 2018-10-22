/*
 * Copyright (c) 2018 QFast.
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

import org.jsoup.safety.Whitelist;

import static com.vaadin.shared.ui.ContentMode.HTML;
import static org.jsoup.Jsoup.clean;

/**
 * @author Mawaziny
 */
public class Img extends Label {

    private static final long serialVersionUID = -2879612973232108854L;

    private String src;
    private String styleNames;
    private String width;

    public Img() {
    }

    public Img(String src) {
        this.src = clean(src, Whitelist.none());
    }

    public Img(String src, String styleNames) {
        this(src);
        this.styleNames = clean(styleNames, Whitelist.none());
    }

    @Override
    public void setValue(String src) {
        this.src = src;
    }

    public Img withValue(String src) {
        setValue(src);
        return this;
    }

    public Img withWidth(String width) {
        setWidth(width);
        this.width = width;
        return this;
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        render();
        super.beforeClientResponse(initial);
    }

    private void render() {
        setSizeUndefined();
        if (src != null) {
            setContentMode(HTML);
            super.setValue("<img " +
                    (width != null ? "width='" + width + "' " : "") +
                    "src='" + src + "' " +
                    (styleNames != null ? "class='" + styleNames + "' " : "") +
                    "/>");
            src = null;
        }
    }
}