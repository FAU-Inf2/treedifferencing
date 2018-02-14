/*
 * This file is part of GumTree.
 *
 * GumTree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GumTree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GumTree.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2015-2016 Georg Dotzler <georg.dotzler@fau.de>
 * Copyright 2015-2016 Marius Kamp <marius.kamp@fau.de>
 */

package com.github.gumtreediff.test;

import com.github.gumtreediff.matchers.MappingStore;
import com.github.gumtreediff.matchers.Matcher;
import com.github.gumtreediff.matchers.OptimizedVersions;
import com.github.gumtreediff.matchers.optimal.zs.ZsMatcher;
import com.github.gumtreediff.tree.ITree;
import com.github.gumtreediff.utils.Pair;
import com.github.gumtreediff.tree.TreeContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMtDiffMatcher {

    @Test
    public void testWithCustomExample() {
        Pair<TreeContext, TreeContext> trees = TreeLoader.getZsCustomPair();
        ITree src = trees.getFirst().getRoot();
        ITree dst = trees.getSecond().getRoot();
        Matcher matcher = new OptimizedVersions.MtDiff(src, dst, new MappingStore(), null);
        matcher.match();
        assertEquals(5, matcher.getMappingSet().size());
        assertTrue(matcher.getMappings().has(src, dst.getChild(0)));
        assertTrue(matcher.getMappings().has(src.getChild(0), dst.getChild(0).getChild(0)));
        assertTrue(matcher.getMappings().has(src.getChild(1), dst.getChild(0).getChild(1)));
        assertTrue(matcher.getMappings().has(src.getChild(1).getChild(0), dst.getChild(0).getChild(1).getChild(0)));
        assertTrue(matcher.getMappings().has(src.getChild(1).getChild(2), dst.getChild(0).getChild(1).getChild(2)));
    }

}