package com.tkartas.recipeapp.converters;

import com.tkartas.recipeapp.commands.CategoryCommand;
import com.tkartas.recipeapp.commands.IngredientCommand;
import com.tkartas.recipeapp.commands.NotesCommand;
import com.tkartas.recipeapp.commands.RecipeCommand;
import com.tkartas.recipeapp.domain.Difficulty;
import com.tkartas.recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {

    public static final long RECIPE_ID = 1L;
    public static final int COOK_TIME = Integer.valueOf("5");
    public static final int PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final int SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final long CAT_ID_1 = 1L;
    public static final long CAT_ID2 = 2L;
    public static final long INGRED_ID_1 = 3L;
    public static final long INGRED_ID_2 = 4L;
    public static final long NOTES_ID = 9L;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter=new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void oonvert(){
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        recipeCommand.setNotes(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID2);

        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);

        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);

        //when
        Recipe recipe  = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}