package com.tkartas.recipeapp.converters;

import com.tkartas.recipeapp.commands.CategoryCommand;
import com.tkartas.recipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION="description";
    public static final Long LONG_VALUE=1L;

    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter=new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category category=new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand command=converter.convert(category);

        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
    }
}