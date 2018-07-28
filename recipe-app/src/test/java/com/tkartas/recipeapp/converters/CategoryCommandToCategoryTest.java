package com.tkartas.recipeapp.converters;

import com.tkartas.recipeapp.commands.CategoryCommand;
import com.tkartas.recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final String DESCRIPTION="description";
    public static final long LONG_VALUE=1L;

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter=new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        //given
        CategoryCommand command=new CategoryCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        Category category=converter.convert(command);

        //then
        assertNotNull(category);
        assertEquals(LONG_VALUE,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());
    }
}