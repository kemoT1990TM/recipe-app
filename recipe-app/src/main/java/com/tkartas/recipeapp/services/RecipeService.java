package com.tkartas.recipeapp.services;

import com.tkartas.recipeapp.commands.RecipeCommand;
import com.tkartas.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(long id);

    void deleteById(long id);
}
