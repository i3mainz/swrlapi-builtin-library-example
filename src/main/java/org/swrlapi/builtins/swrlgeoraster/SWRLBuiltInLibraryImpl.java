package org.swrlapi.builtins.swrlgeoraster;

import org.swrlapi.builtins.AbstractSWRLBuiltInLibrary;
import org.swrlapi.builtins.arguments.SWRLBuiltInArgument;
import org.swrlapi.exceptions.InvalidSWRLBuiltInArgumentException;
import org.swrlapi.exceptions.SWRLBuiltInException;

import com.vividsolutions.jts.algorithm.distance.DiscreteHausdorffDistance;
import com.vividsolutions.jts.algorithm.match.AreaSimilarityMeasure;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

//import de.hsmainz.cs.semgis.reasoning.swrl.GMLLiteral;
//import de.hsmainz.cs.semgis.reasoning.swrl.WKTLiteral;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SWRLBuiltInLibraryImpl extends AbstractSWRLBuiltInLibrary
{
  private static final String[] BuiltInNames = 
	  { "st_hasAreaSizeBiggerThan", "st_hasAreaSizeSmallerThan", "st_contains","st_coveredBy","st_covers","st_crosses",
			  "st_hasDimension","st_disjoint","st_equals","st_equalsExact","st_intersects",
			  "st_isClosed","st_isEmpty","st_isLineString","st_isMultiPoint","st_isMultiPolygon","st_isMultiLineString",
			  "st_isMultiSurface","st_isPoint","st_isPolygon","st_isPolyhedralSurface","st_isRectangle","st_isSimple","st_isRing","st_isValid",
			  "st_isSurface","st_isTriangle","st_isTin","st_isWithinDistance",
			  "st_length","st_numGeometries","st_numPoints","st_overlaps",
			  "st_symDifference","st_touches","st_union","st_within" };

  private static final String Namespace = "http://semgis.de/spatial/swrlgeo#";

  /**
   * Constructor for this class.
   */
  public SWRLBuiltInLibraryImpl()
  {
    super("swrlgeo", Namespace, new HashSet<>(Arrays.asList(BuiltInNames)));
  }
  
  /**
   * Parses a String argument in WKT to a vector graphic geometry representation.
   * @param id the argument number
   * @param arguments the array of arguments
   * @return the parsed geometry
   * @throws SWRLBuiltInException
   */
  public Geometry getArgumentAsACoverage(Integer id,List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException {
	  WKTReader reader = new WKTReader();
	  try {
		return reader.read(getLiteralArgumentAsAString(id, arguments));
	} catch (ParseException e) {
		System.out.println(e.getMessage());
		throw new SWRLBuiltInException("Geometry failed to parse: "+getLiteralArgumentAsAString(id, arguments));
	}
  }

  @Override public void reset() { }
 
  /**
   * Calculates if a geometry contains another geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the second geometry is contained in the first, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_contains(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);
    Geometry geom2=getArgumentAsAGeometry(1, arguments);    
    return geom1.contains(geom2);
  }
  /**
   * Indicates if the first geometry is covered by the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is covered by the second geometry, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_coveredBy(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);
    Geometry geom2=getArgumentAsAGeometry(1, arguments);    
    return geom1.coveredBy(geom2);
  }

  /**
   * Indicates if the first geometry is covers the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry covers the second geometry
   * @throws SWRLBuiltInException on error
   */
  public boolean st_covers(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);
    Geometry geom2=getArgumentAsAGeometry(1, arguments);    
    return geom1.covers(geom2);
  }
  
  /**
   * Indicates if the first geometry is crosses the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry crosses the second geometry
   * @throws SWRLBuiltInException on error
   */
  public boolean st_crosses(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    return geom1.crosses(geom2);
  }
  
  /**
   * Indicates if the first geometry is disjoint with the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is disjoint with the second geometry, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_disjoint(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);
    Geometry geom2=getArgumentAsAGeometry(1, arguments);    
    return geom1.disjoint(geom2);
  }
  
  /**
   * Indicates if the first geometry equals the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry equals the second geometry, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_equals(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    return geom1.equals(geom2);
  }
  
  public boolean st_equalsExact(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    return geom1.equalsExact(geom2);
  }
  
  public boolean st_hasAreaSizeBiggerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Double areasize=getArgumentAsADouble(1,arguments);   
    return geom1.getArea()>areasize;
  }
  
  public boolean st_hasAreaSizeEqualTo(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Double areasize=getArgumentAsADouble(1,arguments);   
    return geom1.getArea()==areasize;
  }
  
  public boolean st_hasAreaSizeSmallerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Double areasize=getArgumentAsADouble(1,arguments);   
    return geom1.getArea()<areasize;
  }
  
  public boolean st_hasDimension(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    int dimension=getArgumentAsAnInt(1, arguments);
    return geom1.getDimension()==dimension;
  }
  
  public boolean st_hasDistanceBiggerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double distance=getArgumentAsADouble(2,arguments);
    return geom1.distance(geom2)>distance;
  }
  
  public boolean st_hasDistanceEqualTo(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double distance=getArgumentAsADouble(2,arguments);
    return geom1.distance(geom2)==distance;
  }
  
  public boolean st_hasDistanceSmallerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double distance=getArgumentAsADouble(2,arguments);
    return geom1.distance(geom2)<distance;
  }

  public boolean st_hasEqualLength(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    return geom1.getLength()==geom2.getLength();
  }  

  public boolean st_hasEqualNumPoints(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    return geom1.getNumPoints()==geom2.getNumPoints();
  }
  
  
  /**
   * Indicates if the given geometry has a length of the given value.
   * @param arguments the first and second geometry as Literal
   * @return true if the given geometry has a length of the given value, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_hasLengthEqualTo(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Double equalTo=getArgumentAsADouble(1, arguments);
    return geom1.getLength()==equalTo;
  }

  /**
   * Indicates if the given geometry has a length of the given value.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry overlaps with the second geometry, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_hasLengthGreaterThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Double greaterThan=getArgumentAsADouble(1, arguments);
    return geom1.getLength()>greaterThan;
  }

  public boolean st_hasLengthSmallerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Double greaterThan=getArgumentAsADouble(1, arguments);
    return geom1.getLength()<greaterThan;
  }
  
  /**
   * Indicates if the given geometry is comprised of the given number of geometries.
   * @param arguments the given geometry as Literal
   * @return true if the geometry is comprised of the given number of geometries
   * @throws SWRLBuiltInException on error
   */
  public boolean st_hasNumGeometries(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    int numGeoms=getArgumentAsAnInt(1, arguments);
    return geom1.getNumGeometries()==numGeoms;
  }
  
  /**
   * Indicates if the first geometry is intersects with the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry intersects with the second geometry
   * @throws SWRLBuiltInException on error
   */
  public boolean st_intersects(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    return geom1.intersects(geom2);
  }

  /**
   * Indicates if the given geometry is a circular String.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is a circular String, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isCircularString(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "CIRCULARSTRING".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if a LineString geometry is closed.
   * @param arguments the geometry
   * @return true if the geometry is a LineString and closed, false if the geometry is a LineString and not closed
   * @throws SWRLBuiltInException if the geometry is not a LineString
   */
  public boolean st_isClosed(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);  
    if(st_isLineString(arguments)) {
        LineString geom1=(LineString)(Object)getLiteralArgumentAsAString(0, arguments);
        return geom1.isClosed();
    }else {
    	throw new InvalidSWRLBuiltInArgumentException("Closedness can only be measured on a LineString");
    }
  }

  /**
   * Indicates if the given geometry is a compound curve.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is a compound curve, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isCompoundCurve(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "COMPOUNDCURVE".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a curve.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is a curve, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isCurve(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "CURVE".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is empty.
   * @param arguments geometry as Literal
   * @return true if the geometry is empty, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isEmpty(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return geom1.isEmpty();
  }
  
  public boolean st_hasHausdorffDistanceGreaterThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double value=getArgumentAsADouble(2, arguments);
	DiscreteHausdorffDistance distance=new DiscreteHausdorffDistance(geom1, geom2);
    return distance.distance()>value;
  }
  
  public boolean st_hasHausdorffDistanceSmallerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double value=getArgumentAsADouble(2, arguments);
	DiscreteHausdorffDistance distance=new DiscreteHausdorffDistance(geom1, geom2);
    return distance.distance()<value;
  }
  
  public boolean st_hasHausdorffDistanceEqualTo(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double value=getArgumentAsADouble(2, arguments);
	DiscreteHausdorffDistance distance=new DiscreteHausdorffDistance(geom1, geom2);
    return distance.distance()==value;
  }
  
  public boolean st_hasAreaSimilarityMeasureEqualTo(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double value=getArgumentAsADouble(2, arguments);
    AreaSimilarityMeasure area=new AreaSimilarityMeasure();
    Double measure=area.measure(geom1,geom2);
    return measure==value;
  }
  
  public boolean st_hasAreaSimilarityMeasureBiggerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double value=getArgumentAsADouble(2, arguments);
    AreaSimilarityMeasure area=new AreaSimilarityMeasure();
    Double measure=area.measure(geom1,geom2);
    return measure>value;
  }
  
  public boolean st_hasAreaSimilarityMeasureSmallerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double value=getArgumentAsADouble(2, arguments);
    AreaSimilarityMeasure area=new AreaSimilarityMeasure();
    Double measure=area.measure(geom1,geom2);
    return measure<value;
  }
  
  /**
   * Indicates if the given geometry is a LineString.
   * @param arguments geometry as Literal
   * @return true if the geometry is a LineString, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isLineString(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "LINESTRING".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a MultiCurve.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is a MultiCurve, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isMultiCurve(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "MULTICURVE".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a MultiLineString.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is a MultiLineString, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isMultiLineString(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "MULTILINESTRING".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a MultiPoint.
   * @param arguments the given geometry as Literal
   * @return true if the first geometry is a MultiPoint, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isMultiPoint(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "MULTIPOINT".equals(geom1.getGeometryType().toUpperCase());
  }
  
  
  /**
   * Indicates if the given geometry is a Multipolygon.
   * @param arguments geometry as Literal
   * @return true if the geometry is Multipolygon, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isMultiPolygon(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "MULTIPOLYGON".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a MultiSurface.
   * @param arguments the given geometry as Literal
   * @return true if the first geometry is a MultiSurface, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isMultiSurface(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "MULTISURFACE".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a point.
   * @param arguments geometry as Literal
   * @return true if the geometry is a point, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isPoint(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "POINT".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a Polygon.
   * @param arguments geometry as Literal
   * @return true if the geometry is a Polygon, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isPolygon(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "POLYGON".equals(geom1.getGeometryType().toUpperCase());
  }

  /**
   * Indicates if the given geometry is a PolyhedralSurface.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is a PolyhedralSurface, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isPolyhedralSurface(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "POLYHEDRALSURFACE".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a rectangle.
   * @param arguments geometry as Literal
   * @return true if the geometry is a rectangle, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isRectangle(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return geom1.isRectangle();
  }
  
  /**
   * Indicates if a LineString geometry is a ring.
   * @param arguments the geometry
   * @return true if the geometry is a LineString and a ring, false if the geometry is a LineString and not closed
   * @throws SWRLBuiltInException if the geometry is not a LineString
   */
  public boolean st_isRing(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);  
    if(st_isLineString(arguments)) {
        LineString geom1=(LineString)(Object)getLiteralArgumentAsAString(0, arguments);
        return geom1.isRing();
    }else {
    	throw new InvalidSWRLBuiltInArgumentException("Ring attributes can only be measured on a LineString");
    }
  }
  
  /**
   * Indicates if the given geometry is simple.
   * @param arguments geometry as Literal
   * @return true if the geometry is simple, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isSimple(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return geom1.isSimple();
  }
  
  /**
   * Indicates if the given geometry is a Surface.
   * @param arguments the given geometry as Literal
   * @return true if the first geometry is a Surface, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isSurface(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "SURFACE".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a tin.
   * @param arguments the given geometry as Literal
   * @return true if the first geometry is a Tin, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isTin(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "TIN".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is a Triangle.
   * @param arguments the given geometry as Literal
   * @return true if the first geometry is a Triangle, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isTriangle(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return "TRIANGLE".equals(geom1.getGeometryType().toUpperCase());
  }
  
  /**
   * Indicates if the given geometry is valid.
   * @param arguments geometry as Literal
   * @return true if the geometry is valid, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isValid(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 1);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    return geom1.isValid();
  }
  
  /**
   * Indicates if the first geometry isWithinDistance of a given value to the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry equals the second geometry, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_isWithinDistance(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 3);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    Double distance=getArgumentAsADouble(2, arguments);
    return geom1.isWithinDistance(geom2, distance);
  }
  
  public boolean st_numPointsGreaterThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    int amount=getArgumentAsAnInt(1, arguments);
    return geom1.getNumPoints()>amount;
  }
  
  public boolean st_numPointsSmallerThan(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    int amount=getArgumentAsAnInt(1, arguments);
    return geom1.getNumPoints()<amount;
  }
  
  /**
   * Indicates if the first geometry overlaps with the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry overlaps with the second geometry, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_overlaps(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);
    Geometry geom2=getArgumentAsAGeometry(1, arguments);    
    return geom1.overlaps(geom2);
  }
  
  /**
   * Indicates if the first geometry touches the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry touches the second geometry, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_touches(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);  
    Geometry geom2=getArgumentAsAGeometry(1, arguments);
    return geom1.touches(geom2);
  }

  /**
   * Calculates if the first geometry is within the second geometry.
   * @param arguments the first and second geometry as Literal
   * @return true if the first geometry is located within the second geometry, false otherwise
   * @throws SWRLBuiltInException on error
   */
  public boolean st_within(List<SWRLBuiltInArgument> arguments) throws SWRLBuiltInException
  {   
    checkNumberOfArgumentsEqualTo(arguments.size(), 2);    
    Geometry geom1=getArgumentAsAGeometry(0, arguments);
    Geometry geom2=getArgumentAsAGeometry(1, arguments);    
    return geom1.within(geom2);
  }
  
}