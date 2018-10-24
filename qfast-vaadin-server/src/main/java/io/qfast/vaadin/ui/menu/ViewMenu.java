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

package io.qfast.vaadin.ui.menu;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import io.qfast.vaadin.ui.Button;
import io.qfast.vaadin.ui.Label;
import io.qfast.vaadin.ui.VerticalLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.vaadin.icons.VaadinIcons.LIST;
import static com.vaadin.icons.VaadinIcons.NEWSPAPER;
import static com.vaadin.shared.ui.ContentMode.HTML;
import static com.vaadin.ui.Alignment.TOP_CENTER;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_ICON_ONLY;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_SMALL;
import static com.vaadin.ui.themes.ValoTheme.LABEL_H4;
import static com.vaadin.ui.themes.ValoTheme.LABEL_NO_MARGIN;
import static com.vaadin.ui.themes.ValoTheme.MENU_ITEM;
import static com.vaadin.ui.themes.ValoTheme.MENU_LOGO;
import static com.vaadin.ui.themes.ValoTheme.MENU_PART;
import static com.vaadin.ui.themes.ValoTheme.MENU_ROOT;
import static com.vaadin.ui.themes.ValoTheme.MENU_SUBTITLE;
import static com.vaadin.ui.themes.ValoTheme.MENU_TITLE;
import static java.util.stream.Collectors.toList;

public class ViewMenu extends CssLayout {

    private static final long serialVersionUID = 5843646442790370361L;

    private final CssLayout menu = new CssLayout();
    private final Label header = new Label().withStyle(LABEL_H4, LABEL_NO_MARGIN).withWidthUndefined();
    private final VerticalLayout top = new VerticalLayout().withSpacing().withFullWidth();
    private final Map<String, Button> viewIdToButton = new HashMap<>();
    private Button selectedButton;
    private Set<String> viewIds = new HashSet<>();

    private Set<ViewMenuItem> viewMenuItems;

    public void init(Component userMenu, String mainCaption, String mainView, String icon) {
        setPrimaryStyleName(MENU_ROOT);

        menu.setId("app-menu");
        menu.addStyleName(MENU_PART);
        addComponent(menu);

        Image image = new Image();
        image.setSource(new ThemeResource("img/logo-small.png"));
        image.addStyleName(MENU_LOGO);
        top.addComponent(image);

        if (!isNULL(header.getValue())) {
            top.addComponent(header);
        }

        top.setDefaultComponentAlignment(TOP_CENTER);
        top.addStyleName(MENU_TITLE);
        menu.addComponent(top);

        Button showMenu = new Button(LIST).withStyle(BUTTON_PRIMARY, BUTTON_SMALL, BUTTON_ICON_ONLY, "valo-menu-toggle");
        MenuButton.extend(showMenu);
        menu.addComponent(showMenu);

        if (userMenu != null) {
            userMenu.addStyleName("user-menu");
            menu.addComponent(userMenu);
        }

        createMenuItems(mainCaption, mainView, icon);

        addAttachListener(event -> {
            Navigator navigator = UI.getCurrent().getNavigator();
            if (navigator != null) {
                String state = navigator.getState();
                if (state == null) {
                    state = "";
                }
                Button b = viewIdToButton.get(state);
                if (b != null) {
                    emphasisAsSelected(b);
                }
            }
        });
    }

    public boolean isUserHavingAccessToView(String viewId) {
        return viewIds != null && viewIds.contains(viewId);
    }

    private void createMenuItems(String mainCaption, String mainView, String icon) {
        final CssLayout menuItemsLayout = new CssLayout();
        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);

        Button mainBtn = getButtonFor(mainCaption, mainView, icon, null);
        viewIdToButton.put(mainView, mainBtn);
        viewIds.add(mainView);
        menuItemsLayout.addComponent(mainBtn);

        List<ViewMenuItem> enabledViewMenuItems =
                viewMenuItems.stream().filter(v -> isNULL(v.getViewId())).collect(toList());
        enabledViewMenuItems.forEach(item -> {
            Label subTitle;
            if (isNULL(item.getBadge())) {
                subTitle = new Label(item.getCaption());
            } else {
                subTitle = new Label(item.getCaption() + getBadge(item.getBadge()), HTML);
            }
            subTitle.addStyleName(MENU_SUBTITLE);
            subTitle.addStyleName(LABEL_H4);
            subTitle.setSizeUndefined();
            menuItemsLayout.addComponent(subTitle);

            item.getViewMenuItems().forEach(subItem -> {
                if (!isNULL(subItem.getViewId())) {
                    viewIds.add(subItem.getViewId());
                    if (subItem.isEnabled()) {
                        Button button =
                                getButtonFor(subItem.getCaption(), subItem.getViewId(), subItem.getIcon(),
                                        subItem.getBadge());
                        viewIdToButton.put(subItem.getViewId(), button);
                        menuItemsLayout.addComponent(button);
                    }
                }
            });
        });
    }

    public Set<ViewMenuItem> getViewMenuItems() {
        return viewMenuItems;
    }

    public void setViewMenuItems(Set<ViewMenuItem> viewMenuItems) {
        this.viewMenuItems = viewMenuItems;
    }

    private String getBadge(String badge) {
        return "<span class='valo-menu-badge'>" + badge + "</span>";
    }

    private Button getButtonFor(String caption, String viewId, String icon, String badge) {
        Button button;
        if (isNULL(badge)) {
            button = new Button(caption, e -> navigateTo(viewId));
        } else {
            button = new Button(caption + getBadge(badge), e -> navigateTo(viewId));
            button.setCaptionAsHtml(true);
        }
        button.setPrimaryStyleName(MENU_ITEM);
        button.setIcon(icon == null ? NEWSPAPER : VaadinIcons.valueOf(icon));
        MenuButton.extend(button);
        return button;
    }

    private void emphasisAsSelected(Button button) {
        if (selectedButton != null) {
            selectedButton.removeStyleName("selected");
        }
        button.addStyleName("selected");
        selectedButton = button;
    }

    public void setMenuTitle(String menuTitle) {
        header.setValue(menuTitle);
    }

    public void navigateTo(final String viewId) {
        menu.removeStyleName("valo-menu-visible");
        Button button = viewIdToButton.get(viewId);
        if (button != null) {
            emphasisAsSelected(button);
        }
        UI.getCurrent().getNavigator().navigateTo(viewId);
    }

    private boolean isNULL(Object text) {
        if (text != null) {
            String strTest = text.toString().trim();
            if (!strTest.isEmpty() && !strTest.equalsIgnoreCase("null")) {
                return false;
            }
        }
        return true;
    }
}
