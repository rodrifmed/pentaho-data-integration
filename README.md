<p align="center">
  <h1 align="center">pentaho-data-integration</h1>
  <p align="center">Simple project for execute pentaho data integration from pentaho's java api</p>
</p>

## What is this project for?

 - This project is a library to help schedule or execute pentaho data integration transformations (ktr) in your java projects.
 - The project was built using the pentaho's java api
 - http://www.pentaho.com/product/data-integration
 
## How to install?

 1) Donwload the tag 1.0.0 (https://github.com/rodrifmed/pentaho-data-integration/tree/1.0.0)
 2) Run gradle command publishToMavenLocal
 3) In your project put the dependency com.rodrifmed.dataintegration:pentaho-dataintegration:1.0.0

## How to use?

It's simple, you just need to create a TransformationManager, passing parameters if you need, and then "executeTransformation" passing
your transformation path.

- Example simple use:
```java

  TransformationManager manager = new TransformationManager();

  Map<String, String> parameters = new HashMap<String, String>();
  parameters.put("HELLO_WORLD", "Hello World");

  manager.executeTransformation("transformations/hello_world.ktr");
  
```

- Example database use:
- Note: You have to create a connection name in your ktr
```java

 TransformationManager manager = new TransformationManager();

  Map<String, String> parameters = new HashMap<String, String>();
  parameters.put("HELLO_WORLD", "Hello World");
  
  manager.setParameters(parameters);
  
  manager.setDbHostName("<YOUR-DB-HOST>");
  manager.setDbPort("<YOUR-DB-PORT>");
  manager.setDbName("<YOUR-DB-NAME>");
  manager.setDbUerName("<YOUR-DB-USER-NAME>");
  manager.setDbPassword("<YOUR-DB-PASSWORD>");
  manager.setConnectionName("<YOUR-CONNECTION-NAME>");

  manager.executeTransformation("transformations/your_ktr.ktr");

```

## TODO-LIST

 - Put the project in the maven public repository
 - Create unit tests
