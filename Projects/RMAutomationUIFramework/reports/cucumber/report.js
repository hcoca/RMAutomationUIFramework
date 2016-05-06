$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/admin/resources-associations/resourcesAssociations.feature");
formatter.feature({
  "id": "resources-associations",
  "description": "",
  "name": "Resources associations",
  "keyword": "Feature",
  "line": 1
});
formatter.before({
  "duration": 6660526100,
  "status": "passed"
});
formatter.before({
  "duration": 49365200,
  "status": "passed"
});
formatter.before({
  "duration": 47505700,
  "status": "passed"
});
formatter.scenario({
  "id": "resources-associations;the-quantity-of-the-resources-associated-to-one-room-is-edited-after-changing-the-value-of-this",
  "description": "",
  "name": "The quantity of the resources associated to one room is edited after changing the value of this",
  "keyword": "Scenario",
  "line": 4,
  "type": "scenario"
});
formatter.step({
  "name": "I am on the Conferences Rooms page",
  "keyword": "Given ",
  "line": 5
});
formatter.step({
  "name": "I associate a resource on resources association page",
  "keyword": "And ",
  "line": 6
});
formatter.step({
  "name": "I modify the quantity of the of the associated resource",
  "keyword": "When ",
  "line": 7
});
formatter.step({
  "name": "I can see that the quantity modified is displayed",
  "keyword": "Then ",
  "line": 8
});
formatter.match({
  "location": "TheQuantityOfTheResourceAssociatedsEdited.i_am_on_the_Conferences_Rooms_page()"
});
formatter.result({
  "duration": 4001048900,
  "status": "passed"
});
formatter.match({
  "location": "TheQuantityOfTheResourceAssociatedsEdited.i_associate_a_resource_on_resources_association_page()"
});
formatter.result({
  "duration": 4628168600,
  "status": "passed"
});
formatter.match({
  "location": "TheQuantityOfTheResourceAssociatedsEdited.i_modify_the_quantity_of_the_of_the_associated_resource()"
});
formatter.result({
  "duration": 2727054400,
  "status": "passed"
});
formatter.match({
  "location": "TheQuantityOfTheResourceAssociatedsEdited.i_can_see_that_the_quantity_modified_is_displayed()"
});
formatter.result({
  "duration": 2262198000,
  "status": "passed"
});
formatter.after({
  "duration": 2239000,
  "status": "passed"
});
formatter.after({
  "duration": 3462300,
  "status": "passed"
});
formatter.after({
  "duration": 1256500,
  "status": "passed"
});
formatter.after({
  "duration": 1050500,
  "status": "passed"
});
});