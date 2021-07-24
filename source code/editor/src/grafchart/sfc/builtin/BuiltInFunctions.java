package grafchart.sfc.builtin;

import grafchart.sfc.DPWSObject;
import grafchart.sfc.Utils;
import java.util.HashMap;
import java.util.Map;

public class BuiltInFunctions
{
  private static boolean isInitiated = false;
  private static Map<String, AbstractExecutable> functions;
  
  private static void init()
  {
    if (!isInitiated)
    {
      functions = new HashMap();
      addFunction(new FuncAbs());
      addFunction(new FuncAddPalette());
      addFunction(new FuncApplyStaticMethod());
      addFunction(new FuncClearMessages());
      addFunction(new FuncCompile());
      addFunction(new FuncConnectCCOM());
      addFunction(new FuncCos());
      addFunction(new FuncCurrentTime());
      addFunction(new FuncDelete());
      addFunction(new FuncDisconnectCCOM());
      addFunction(new FuncDPWSGetEvent());
      addFunction(new FuncDPWSGetFault());
      addFunction(new FuncDPWSHasEvent());
      addFunction(new FuncDPWSHasFault());
      addFunction(new FuncDPWSSubscribe());
      addFunction(new FuncDPWSUnsubscribe());
      addFunction(new FuncEvaluateAll());
      addFunction(new FuncExec());
      addFunction(new FuncFormat());
      addFunction(new FuncFreeMemory());
      addFunction(new FuncGetCCOMLoginName());
      addFunction(new FuncGetCCOMPort());
      addFunction(new FuncGetCCOMServer());
      addFunction(new FuncGetTimestamp());
      addFunction(new FuncIntToBlue());
      addFunction(new FuncIntToGreen());
      addFunction(new FuncIntToRed());
      addFunction(new FuncIsConnectedToCCOM());
      addFunction(new FuncLCReceived());
      addFunction(new FuncLCSend());
      addFunction(new FuncLoad());
      addFunction(new FuncLoadCompile());
      addFunction(new FuncLoadCompileStart());
      addFunction(new FuncMax());
      addFunction(new FuncMaxMemory());
      addFunction(new FuncMin());
      addFunction(new FuncNew());
      addFunction(new FuncPrintln());
      addFunction(new FuncRandom());
      addFunction(new FuncRemovePalette());
      addFunction(new FuncRGBToInt());
      addFunction(new FuncSetCCOMLoginName());
      addFunction(new FuncSetCCOMPort());
      addFunction(new FuncSetCCOMServer());
      addFunction(new FuncSin());
      addFunction(new FuncSqrt());
      addFunction(new FuncStart());
      addFunction(new FuncStop());
      addFunction(new FuncStringApply());
      addFunction(new FuncSubscribe());
      addFunction(new FuncTotalMemory());
      addFunction(new FuncUnsubscribe());
      addFunction(new FuncWriteMessage());
      addFunction(new FuncXMLAdd());
      addFunction(new FuncXMLFetch());
      addFunction(new MethodAdd());
      addFunction(new MethodAddFirst());
      addFunction(new MethodAddItem());
      addFunction(new MethodAppend());
      addFunction(new MethodClear());
      addFunction(new MethodContains());
      addFunction(new MethodEcho());
      addFunction(new MethodEvaluate());
      addFunction(new MethodGet());
      addFunction(new MethodGetBkColor());
      addFunction(new MethodGetDestination());
      addFunction(new MethodGetFillColor());
      addFunction(new MethodGetFont());
      addFunction(new MethodGetFontSize());
      addFunction(new MethodGetHandle());
      addFunction(new MethodGetHeight());
      addFunction(new MethodGetItem());
      addFunction(new MethodGetMustReply());
      addFunction(new MethodGetNumItems());
      addFunction(new MethodGetOrigin());
      addFunction(new MethodGetPenColor());
      addFunction(new MethodGetPenStyle());
      addFunction(new MethodGetPenWidth());
      addFunction(new MethodGetPointX());
      addFunction(new MethodGetPointY());
      addFunction(new MethodGetPublish());
      addFunction(new MethodGetSelectedIndex());
      addFunction(new MethodGetSelectedItem());
      addFunction(new MethodGetSendHandle());
      addFunction(new MethodGetText());
      addFunction(new MethodGetTextColor());
      addFunction(new MethodGetTextSize());
      addFunction(new MethodGetTickTime());
      addFunction(new MethodGetTopic());
      addFunction(new MethodGetType());
      addFunction(new MethodGetWidth());
      addFunction(new MethodGetWorkspaceColor());
      addFunction(new MethodGetXLocation());
      addFunction(new MethodGetXML());
      addFunction(new MethodGetYLocation());
      addFunction(new MethodHideWorkspace());
      addFunction(new MethodIndexOf());
      addFunction(new MethodIsBold());
      addFunction(new MethodIsEmpty());
      addFunction(new MethodIsUpdated());
      addFunction(new MethodLength());
      addFunction(new MethodLoadFile());
      addFunction(new MethodLoadURL());
      addFunction(new MethodOutputMessage());
      addFunction(new MethodPublishMessage());
      addFunction(new MethodRemove());
      addFunction(new MethodRemoveItem());
      addFunction(new MethodSample());
      addFunction(new MethodSampleAll());
      addFunction(new MethodSendMessage());
      addFunction(new MethodSet());
      addFunction(new MethodSetBkColor());
      addFunction(new MethodSetBold());
      addFunction(new MethodSetDestination());
      addFunction(new MethodSetEnabled());
      addFunction(new MethodSetFillColor());
      addFunction(new MethodSetFont());
      addFunction(new MethodSetFontSize());
      addFunction(new MethodSetHandle());
      addFunction(new MethodSetHeight());
      addFunction(new MethodSetItem());
      addFunction(new MethodSetLocation());
      addFunction(new MethodSetMustReply());
      addFunction(new MethodSetOrigin());
      addFunction(new MethodSetPenColor());
      addFunction(new MethodSetPenStyle());
      addFunction(new MethodSetPenWidth());
      addFunction(new MethodSetPoint());
      addFunction(new MethodSetPublish());
      addFunction(new MethodSetText());
      addFunction(new MethodSetTextColor());
      addFunction(new MethodSetTextSize());
      addFunction(new MethodSetTopic());
      addFunction(new MethodSetType());
      addFunction(new MethodSetUpdated());
      addFunction(new MethodSetVisible());
      addFunction(new MethodSetWidth());
      addFunction(new MethodSetWorkspaceColor());
      addFunction(new MethodShowWorkspace());
      addFunction(new MethodShowWorkspaceAt());
      addFunction(new MethodSize());
      addFunction(new MethodSpawn());
      addFunction(new MethodWriteFile());
      addFunction(new MethodS());
      addFunction(new MethodT());
      addFunction(new MethodX());
      addFunction(new MethodXEventDown());
      addFunction(new MethodXEventUp());
      isInitiated = true;
    }
  }
  
  private static void addFunction(AbstractExecutable paramAbstractExecutable)
  {
    functions.put(paramAbstractExecutable.getName(), paramAbstractExecutable);
  }
  
  public static String getError(String paramString, Evaluatable[] paramArrayOfEvaluatable, Object paramObject, Language paramLanguage)
  {
    
    if (!(paramObject instanceof DPWSObject))
    {
      AbstractExecutable localAbstractExecutable = (AbstractExecutable)functions.get(paramString);
      if (localAbstractExecutable != null) {
        return localAbstractExecutable.compile(paramArrayOfEvaluatable, paramObject, paramLanguage);
      }
      return "Invalid function name \"" + paramString + "\".";
    }
    return DPWSExecutable.compile(((DPWSObject)paramObject).getPortType(), paramArrayOfEvaluatable.length, paramString);
  }
  
  public static AbstractExecutable getExecutable(String paramString, Object paramObject)
  {
    
    if (!(paramObject instanceof DPWSObject))
    {
      AbstractExecutable localAbstractExecutable = (AbstractExecutable)functions.get(paramString);
      if (localAbstractExecutable != null) {
        return localAbstractExecutable;
      }
      Utils.writeError("Invalid function name \"" + paramString + "\".");
      new AbstractExecutable("DummyFunction", 0) {};
    }
    return new DPWSExecutable(paramString);
  }
  
  public static enum Language
  {
    Action,  Transition,  All;
    
    private Language() {}
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/BuiltInFunctions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */