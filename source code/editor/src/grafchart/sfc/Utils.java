package grafchart.sfc;

import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoObjectSimpleCollection;
import com.nwoods.jgo.JGoPort;
import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.xml.stream.XMLStreamReader;
import org.jdom.input.stax.StaxBuilder;
import org.jdom.output.XMLOutputter;
import org.soda.dpws.DPWSException;
import org.soda.dpws.util.stax.FragmentStreamReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utils
{
  public static final boolean DEFAULT_BOOL = false;
  public static final String DEFAULT_STRING = "";
  public static final double DEFAULT_REAL = 0.0D;
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  public static final String jpeg = "jpeg";
  public static final String jpg = "jpg";
  public static final String gif = "gif";
  public static final String tiff = "tiff";
  public static final String tif = "tif";
  public static final String png = "png";
  public static final int cSaveVersion_Old = 0;
  public static final int cSaveVersion_ProperSpecialChars = 1;
  public static final int cSaveVersion_UUIDs = 2;
  public static final int cSaveVersion_CommonBoundingRect = 3;
  public static final int cSaveVersion_DPWS = 4;
  public static final int cSaveVersion_TransitionPriority = 5;
  public static final int cSaveVersion_MacroStepResumeMode = 6;
  public static final int cSaveVersion_SocketIOFeatures = 7;
  public static final int cSaveVersion_LabComm = 8;
  public static final int cSaveVersion_Current = 8;
  
  public static String getExtension(File paramFile)
  {
    String str1 = null;
    String str2 = paramFile.getName();
    int i = str2.lastIndexOf('.');
    if ((i > 0) && (i < str2.length() - 1)) {
      str1 = str2.substring(i + 1).toLowerCase();
    }
    return str1;
  }
  
  /**
   * Ouvre un fichier si celui ci existe
   * @param path chemin du fichier à ouvri
   * @return Objet File correspondant au fichier
   */
  public static File newFile(String path)
  {
    File localFile = newFileLight(path);
    if (!localFile.exists())
    {
      writeError("File not found: \"" + localFile.getPath() + "\"");
      System.exit(1);
    }
    return localFile;
  }
  
  public static File newFileLight(String paramString)
  {
    File localFile = new File(paramString);
    try
    {
      localFile = localFile.getCanonicalFile();
    }
    catch (IOException localIOException)
    {
      writeException(localIOException, "File not found, possible malformed path: \"" + paramString + "\"");
      System.exit(1);
    }
    return localFile;
  }
  
  public static ImageIcon newImageIcon(String paramString)
  {
    return new ImageIcon(newFile(Editor.getIconLib() + "/" + paramString).getPath());
  }
  
  public static ArrayList<Referencable> collectionToSymbolTable(JGoObjectSimpleCollection paramJGoObjectSimpleCollection)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJGoObjectSimpleCollection != null)
    {
      JGoListPosition localJGoListPosition = paramJGoObjectSimpleCollection.getFirstObjectPos();
      for (JGoObject localJGoObject = paramJGoObjectSimpleCollection.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = paramJGoObjectSimpleCollection.getObjectAtPos(localJGoListPosition))
      {
        if ((localJGoObject instanceof Referencable)) {
          localArrayList.add((Referencable)localJGoObject);
        }
        localJGoListPosition = paramJGoObjectSimpleCollection.getNextObjectPosAtTop(localJGoListPosition);
      }
    }
    return localArrayList;
  }
  
  public static Readable referencableToReadable(Referencable paramReferencable)
  {
    if (paramReferencable != null)
    {
      if ((paramReferencable instanceof Readable)) {
        return (Readable)paramReferencable;
      }
      writeError("\"" + paramReferencable.toString() + "\" cannot be evaluated.");
    }
    return null;
  }
  
  public static Writable referencableToWritable(Referencable paramReferencable)
  {
    if (paramReferencable != null)
    {
      if ((paramReferencable instanceof Writable)) {
        return (Writable)paramReferencable;
      }
      writeError("\"" + paramReferencable.toString() + "\" cannot be assigned.");
    }
    return null;
  }
  
  public static SymbolTableObject referencableToSymbolTableObject(Referencable paramReferencable)
  {
    return referencableToSymbolTableObject(paramReferencable, true);
  }
  
  public static SymbolTableObject referencableToSymbolTableObject(Referencable paramReferencable, boolean paramBoolean)
  {
    if (paramReferencable != null)
    {
      if (((paramReferencable instanceof SymbolTableObject)) && (!(paramReferencable instanceof ProcedureStep)))
      {
        if (((paramReferencable instanceof LabCommObject)) && (((LabCommObject)paramReferencable).getSymbolTable().isEmpty())) {
          return null;
        }
        return (SymbolTableObject)paramReferencable;
      }
      if (paramBoolean) {
        writeError("\"" + paramReferencable.toString() + "\" is not a workspace (1).");
      }
    }
    return null;
  }
  
  public static Hierarchical referencableToHierarchical(Referencable paramReferencable)
  {
    if (paramReferencable != null)
    {
      if ((paramReferencable instanceof Hierarchical)) {
        return (Hierarchical)paramReferencable;
      }
      writeError("\"" + paramReferencable.toString() + "\" is not a workspace (2).");
    }
    return null;
  }
  
  public static GrafcetProcedure referencableToGrafcetProcedure(Referencable paramReferencable)
  {
    return referencableToGrafcetProcedure(paramReferencable, true);
  }
  
  public static GrafcetProcedure referencableToGrafcetProcedure(Referencable paramReferencable, boolean paramBoolean)
  {
    if (paramReferencable != null)
    {
      if ((paramReferencable instanceof GrafcetProcedure)) {
        return (GrafcetProcedure)paramReferencable;
      }
      if (paramBoolean) {
        writeError("\"" + paramReferencable.toString() + "\" is not a Procedure.");
      }
    }
    return null;
  }
  
  public static void writeError(String paramString)
  {
    writeError(paramString, true);
  }
  
  private static void writeError(String paramString, boolean paramBoolean)
  {
    write('E', paramString, paramBoolean);
  }
  
  public static void writeWarning(String paramString)
  {
    writeWarning(paramString, true);
  }
  
  private static void writeWarning(String paramString, boolean paramBoolean)
  {
    write('W', paramString, paramBoolean);
  }
  
  public static void writeInformation(String paramString)
  {
    writeInformation(paramString, true);
  }
  
  private static void writeInformation(String paramString, boolean paramBoolean)
  {
    write('I', paramString, paramBoolean);
  }
  
  public static void writeDebug(String paramString)
  {
    writeDebug(paramString, true);
  }
  
  private static void writeDebug(String paramString, boolean paramBoolean)
  {
    write('D', paramString, paramBoolean);
  }
  
  public static void writeException(Throwable paramThrowable)
  {
    writeException(paramThrowable, "Exception without error message.");
  }
  
  public static void writeException(Throwable paramThrowable, String paramString)
  {
    writeError(paramString, false);
    System.out.println("Related exception:");
    PrintStream localPrintStream = System.err;
    System.setErr(System.out);
    paramThrowable.printStackTrace();
    System.setErr(localPrintStream);
  }
  
  public static void writeInternalError(String paramString)
  {
    writeError("Internal Error: " + paramString);
  }
  
  private static void write(char paramChar, String paramString, boolean paramBoolean)
  {
    paramString = dateFormat.format(new Date()) + " [" + paramChar + "] " + paramString;
    if (paramBoolean)
    {
      paramString = paramString + " <-- ";
      int i = 0;
      StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
      for (int j = 3; (i == 0) && (j < arrayOfStackTraceElement.length); j++)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[j];
        if ((!localStackTraceElement.getClassName().equals("grafchart.sfc.Utils")) || (!localStackTraceElement.getMethodName().startsWith("write")))
        {
          paramString = paramString + localStackTraceElement.getClassName() + "." + localStackTraceElement.getMethodName() + "()";
          i = 1;
        }
      }
      if (i == 0) {
        paramString = paramString + "???";
      }
    }
    System.out.println(paramString);
  }
  
  public static int getSaveVersion(Node paramNode)
  {
    org.w3c.dom.Element localElement = (org.w3c.dom.Element)paramNode.getOwnerDocument().getFirstChild();
    if (localElement.hasAttribute("saveVersion")) {
      return Integer.parseInt(localElement.getAttribute("saveVersion"));
    }
    return 0;
  }
  
  public static String newlineHack(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; paramString.length() > i; i++) {
      if (paramString.charAt(i) == ';')
      {
        localStringBuffer.append(';');
        if (paramString.length() > i + 1) {
          localStringBuffer.append('\n');
        }
        if ((paramString.length() > i + 1) && (paramString.charAt(i + 1) == ' ')) {
          i++;
        }
      }
      else
      {
        localStringBuffer.append(paramString.charAt(i));
      }
    }
    return localStringBuffer.toString();
  }
  
  public static void locationToTopLeft(JGoObject paramJGoObject, Point paramPoint)
  {
    Point localPoint1 = new Point();
    paramJGoObject.getLocation(localPoint1);
    Point localPoint2 = paramJGoObject.getTopLeft();
    paramPoint.x -= localPoint1.x - localPoint2.x;
    paramPoint.y -= localPoint1.y - localPoint2.y;
  }
  
  public static double parseDoubleStrict(String paramString)
    throws NumberFormatException
  {
    double d = Double.parseDouble(paramString);
    if (Double.isNaN(d)) {
      throw new NumberFormatException("NaN");
    }
    if (Double.isInfinite(d)) {
      throw new NumberFormatException("Infinite");
    }
    return d;
  }
  
  public static double parseDouble(String paramString)
  {
    return parseDouble(paramString, 0.0D);
  }
  
  public static double parseDouble(String paramString, double paramDouble)
  {
    double d = paramDouble;
    try
    {
      d = parseDoubleStrict(paramString);
    }
    catch (NumberFormatException localNumberFormatException) {}
    return d;
  }
  
  public static int parseInt(String paramString)
  {
    return parseInt(paramString, 0);
  }
  
  public static int parseInt(String paramString, int paramInt)
  {
    int i = paramInt;
    try
    {
      i = Integer.parseInt(paramString);
    }
    catch (NumberFormatException localNumberFormatException) {}
    return i;
  }
  
  public static long parseLong(String paramString)
  {
    return parseLong(paramString, 0);
  }
  
  public static long parseLong(String paramString, long paramInt)
  {
    long i = paramInt;
    try
    {
      i = Integer.parseInt(paramString);
    }
    catch (NumberFormatException localNumberFormatException) {}
    return i;
  }
  
  public static void addLinks(JGoPort paramJGoPort, Vector paramVector)
  {
    if (paramJGoPort != null) {
      for (JGoListPosition localJGoListPosition = paramJGoPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = paramJGoPort.getNextLinkPos(localJGoListPosition)) {
        paramVector.add(paramJGoPort.getLinkAtPos(localJGoListPosition));
      }
    }
  }
  
  public static void printXML(org.jdom.Element paramElement)
  {
    writeDebug("\n" + getXML(paramElement));
  }
  
  public static String getXML(org.jdom.Element paramElement)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      new XMLOutputter().output(paramElement, localByteArrayOutputStream);
      return localByteArrayOutputStream.toString();
    }
    catch (IOException localIOException)
    {
      writeException(localIOException);
    }
    return null;
  }
  
  public static String getElementText(org.w3c.dom.Element paramElement)
  {
    String str = "";
    NodeList localNodeList = paramElement.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 3) {
        str = str + localNode.getTextContent();
      }
    }
    return str;
  }
  
  public static boolean xsdBooleanStringToBoolean(String paramString)
  {
    return (paramString.equals("1")) || (paramString.equals("true"));
  }
  
  public static org.jdom.Document readXML(XMLStreamReader paramXMLStreamReader)
    throws DPWSException
  {
    FragmentStreamReader localFragmentStreamReader = new FragmentStreamReader(paramXMLStreamReader);
    StaxBuilder localStaxBuilder = new StaxBuilder();
    try
    {
      return localStaxBuilder.build(localFragmentStreamReader);
    }
    catch (Exception localException)
    {
      writeException(localException);
      throw new DPWSException();
    }
  }
  
  public static enum DataType
  {
    eBool,  eString,  eReal,  eInt;
    
    private DataType() {}
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Utils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */