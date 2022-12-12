INSERT INTO Recipe (id , title, servings , instructions) VALUES(1, 'Boiled Egg', 1, 'Add egg to cold water. Bring water to boil. Cook.');
INSERT INTO Recipe (id , title, servings , instructions) VALUES(2, 'Chocolate Cake', 8, 'Add eggs, flour, chocolate to pan. Bake at 350 in the oven for 1 hour');

ALTER SEQUENCE RECIPE_SEQ RESTART WITH 3;

insert into INGREDIENT ( id,  name, quantity, unit , recipe_id) values (1, 'Flour', 100, 'gram', 1);
insert into INGREDIENT ( id,  name, quantity, unit , recipe_id ) values (2, 'Tomatoes', 200 , 'gram', 2);
insert into INGREDIENT ( id,  name, quantity, unit , recipe_id) values (3, 'Apple', 100, 'gram', 1);
insert into INGREDIENT ( id,  name, quantity, unit , recipe_id ) values (4, 'Salmon', 200, 'gram', 2);
insert into INGREDIENT ( id,  name, quantity, unit , recipe_id) values (5, 'Cheese', 2, 'pieces', 2);

ALTER SEQUENCE INGREDIENT_SEQ RESTART WITH 6;

INSERT INTO CATEGORY (id, name) VALUES(1, 'vegetarian');
INSERT INTO CATEGORY (id, name) VALUES(2, 'vegan');
INSERT INTO CATEGORY (id, name) VALUES(3, 'halal');
INSERT INTO CATEGORY (id, name) VALUES(4, 'gluten_free');
INSERT INTO CATEGORY (id, name) VALUES(5, 'allergy_friendly');

INSERT INTO CATEGORY_RECIPES(RECIPES_ID,categories_id) VALUES (1,1);
INSERT INTO CATEGORY_RECIPES(RECIPES_ID,categories_id) VALUES (1,2);
INSERT INTO CATEGORY_RECIPES(RECIPES_ID,categories_id) VALUES (1,3);
INSERT INTO CATEGORY_RECIPES(RECIPES_ID,categories_id) VALUES (1,4);
INSERT INTO CATEGORY_RECIPES(RECIPES_ID,categories_id) VALUES (1,5);
INSERT INTO CATEGORY_RECIPES(RECIPES_ID,categories_id) VALUES (2,1);
INSERT INTO CATEGORY_RECIPES(RECIPES_ID,categories_id) VALUES (2,2);
INSERT INTO CATEGORY_RECIPES(RECIPES_ID,categories_id) VALUES (2,3);


