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
package io.qfast.vaadin.client.menubutton;

import io.qfast.vaadin.ui.menu.MenuButton;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VButton;
import com.vaadin.shared.ui.Connect;

/**
 * @author Mawaziny
 */
@Connect(MenuButton.class)
public class MenuButtonConnector extends AbstractExtensionConnector {

    private static final long serialVersionUID = 3826845215110497889L;
    private VButton menuButton;
    private ClickHandler clickHandler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent clickEvent) {
            Element menu = DOM.getElementById("app-menu");
            if (menu.getClassName().contains("valo-menu-visible")) {
                menu.removeClassName("valo-menu-visible");
            } else {
                menu.addClassName("valo-menu-visible");
            }
        }
    };

    @Override
    protected void extend(ServerConnector target) {
        menuButton = (VButton) ((ComponentConnector) target).getWidget();
        menuButton.addClickHandler(clickHandler);
    }
}
