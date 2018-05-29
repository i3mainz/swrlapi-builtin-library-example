package org.swrlapi.example;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.util.Collections;
import java.util.Set;

public class SWRLAPIBuiltInExample
{
  public static void main(String[] args)
  {
    try {
      // Create an empty OWL ontology using the OWLAPI
      OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
      OWLOntology ontology = ontologyManager.createOntology();

      Set<OWLOntologyIRIMapper> mappers = Collections.singleton(
        new SimpleIRIMapper(IRI.create("http://www.semanticweb.org/dell/ontologies/2016/6/untitled-ontology-42"),
          IRI.create("file:///Users/moconnor/workspace/common/swrl/examples/import-example/MyBuiltIns.owl")));
      ontologyManager.setIRIMappers(mappers);

      // Create SQWRL query engine using the SWRLAPI
      SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);

      // Create and execute a SQWRL query using the SWRLAPI
      for(@NonNull IRI iri:queryEngine.getSWRLBuiltInIRIs()) {
    	  System.out.println(iri.toString());
      }
      SQWRLResult result = queryEngine
        .runSQWRLQuery("q1", "swrlgeo:st_isValid(\"POINT(0 0)\") -> sqwrl:select(\"IsValid\")");

      // Print the result
      if (result.next())
        System.out.println("Answer: " + result.getLiteral(0).getString());

    } catch (OWLOntologyCreationException e) {
      System.err.println("Error creating OWL ontology: " + e.getMessage());
      System.exit(-1);
    } catch (SWRLParseException e) {
      System.err.println("Error parsing SQWRL query: " + e.getMessage());
      System.exit(-1);
    } catch (SQWRLException e) {
      System.err.println("Error running SQWRL query: " + e.getMessage());
      System.exit(-1);
    } catch (RuntimeException e) {
      System.err.println("Error starting application: " + e.getMessage());
      System.exit(-1);
    }
  }
}
