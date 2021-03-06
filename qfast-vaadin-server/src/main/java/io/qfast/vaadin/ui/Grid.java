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

import java.util.Collection;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;

/**
 * @author Mawaziny
 */
public class Grid<T> extends com.vaadin.ui.Grid<T> {
    private static final long serialVersionUID = 3418354522368192828L;

    public Grid(Collection<T> items) {
        super("", items);
    }

    public Grid<T> withSizeFull() {
        setSizeFull();
        return this;
    }

    public Grid<T> withColumnOrder(String... columns) {
        setColumns(columns);
        setColumnOrder(columns);
        return this;
    }

    public Grid<T> withWidthFull() {
        setWidth(100, PERCENTAGE);
        return this;
    }
}
