package com.tkartas.recipeapp.services;

import com.tkartas.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(long recipeId, long ingredientId);
}
