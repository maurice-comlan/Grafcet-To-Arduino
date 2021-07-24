package grafchart.sfc;

import CCOM.client.CCOM;
import CCOM.client.I_update;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoButton;
import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoDocumentEvent;
import com.nwoods.jgo.JGoDrawable;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoSelection;
import com.nwoods.jgo.JGoText;
import com.nwoods.jgo.JGoViewEvent;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import grafchart.arduino.ArduinoBoard;
import grafchart.arduino.CGeneretor;
import grafchart.dialog.AboutDialog;
import grafchart.dialog.AssignmentButtonDialog;
import grafchart.dialog.BasicListDialog;
import grafchart.dialog.BrowserDialog;
import grafchart.dialog.BrowserNameDialog;
import grafchart.dialog.ByteStreamInDialog;
import grafchart.dialog.ByteStreamOutDialog;
import grafchart.dialog.ConnectDialog;
import grafchart.dialog.ConnectionPostDialog;
import grafchart.dialog.DPWSObjectDialog;
import grafchart.dialog.DrawableNameDialog;
import grafchart.dialog.DrawablePropsDialog;
import grafchart.dialog.GrafcetDialog;
import grafchart.dialog.GraphicalButtonDialog;
import grafchart.dialog.GridOptionsDialog;
import grafchart.dialog.IconDialog;
import grafchart.dialog.InputDialog;
import grafchart.dialog.InternalVariableDialog;
import grafchart.dialog.LabCommObjectDialog;
import grafchart.dialog.MacroStepDialog;
import grafchart.dialog.ProcedureCallsDialog;
import grafchart.dialog.ProcedureParameters;
import grafchart.dialog.ProcedureStepDialog;
import grafchart.dialog.SFCPlotterDialog;
import grafchart.dialog.ServerDialog;
import grafchart.dialog.SetSuperClassDialog;
import grafchart.dialog.ShowWorkspaceDialog;
import grafchart.dialog.SocketInDialog;
import grafchart.dialog.SocketOutDialog;
import grafchart.dialog.StartParameters;
import grafchart.dialog.StepDialog;
import grafchart.dialog.StepFusionSetDialog;
import grafchart.dialog.StepIconDialog;
import grafchart.dialog.StepNameDialog;
import grafchart.dialog.TextPropsDialog;
import grafchart.dialog.TransitionDialog;
import grafchart.dialog.VariableEditorDialog;
import grafchart.dialog.WorkspaceDialog;
import grafchart.dialog.XMLMessageInDialog;
import grafchart.dialog.XMLMessageInProcedureDialog;
import grafchart.dialog.XMLMessageOutDialog;
import grafchart.dpws.Services;
import grafchart.dpws.ServicesDialog;
import grafchart.dpws.db.DPWSPortType;
import grafchart.graphics.MyBorderLayout;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDesktopPane;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJFileChooser;
import grafchart.graphics.MyJFrame;
import grafchart.graphics.MyJInternalFrame;
import grafchart.graphics.MyJMenu;
import grafchart.graphics.MyJMenuBar;
import grafchart.graphics.MyJPanel;
import grafchart.graphics.MyJPopupMenu;
import grafchart.graphics.MyJTabbedPane;
import grafchart.graphics.MyJToolBar;
import grafchart.graphics.MyWindowAdapter;
import grafchart.sfc.actions.ProcCall;
import grafchart.sfc.actions.ProcParam;
import grafchart.sfc.actions.SingleExpression;
import grafchart.sfc.io.LocalIO;
import grafchart.sfc.transitions.ParseException;
import grafchart.sfc.transitions.TransitionParser;
import grafchart.util.ActionCompiler;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import grafchart.util.SymbolTableHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.BadIDException;
import javax.help.CSH;
import javax.help.DefaultHelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jutils.JUtilsException;
import org.jutils.init.Args;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class principale représentant à la fois le JFrame de base
 * 
 */
public class Editor extends MyJFrame implements I_update {

    public static boolean isTest = false;
    AppAction CompileAction = new AppAction("Compile", Utils.newImageIcon("buttons/compile_scaled.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.compileAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.compilationView) && (!getView().getDoc().executing));
        }
    };
    AppAction CompileAllAction = new AppAction("Compile All", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.compileAllAction();
        }

        public boolean canAct() {
            if (Editor.objectHelpMode) {
                return true;
            }
            if (super.canAct()) {
                Iterator localIterator = Editor.topGrafcharts.getStorage().iterator();
                while (localIterator.hasNext()) {
                    GCDocument localGCDocument = (GCDocument) localIterator.next();
                    if (!localGCDocument.executing) {
                        return true;
                    }
                }
            }
            return false;
        }
    };
    AppAction ExecuteAction = new AppAction("Execute", Utils.newImageIcon("buttons/execute_scaled.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.executeAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || (false && (super.canAct()) && (Editor.compilationView) && (Editor.myCurrentView != null) 
                    && (!getView().getDoc().executing) && (getView().getDoc().compiledOK));
        }
    };
    AppAction ExecuteAllAction = new AppAction("Execute All", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_ExecuteMenu_ExecuteAll");
            } else {
                Iterator localIterator = Editor.topGrafcharts.getStorage().iterator();
                while (localIterator.hasNext()) {
                    GCDocument localGCDocument = (GCDocument) localIterator.next();
                    if ((!localGCDocument.executing) && (localGCDocument.compiledOK)) {
                        Editor.this.startDocument(localGCDocument);
                    }
                }
                Editor.updateActions();
            }
        }

        public boolean canAct() {
            if (Editor.objectHelpMode) {
                return true;
            }
            if (super.canAct()) {
                Iterator localIterator = Editor.topGrafcharts.getStorage().iterator();
                while (localIterator.hasNext()) {
                    GCDocument localGCDocument = (GCDocument) localIterator.next();
                    if ((!localGCDocument.executing) && (localGCDocument.compiledOK)) {
                        return true;
                    }
                }
            }
            return false;
        }
    };
    AppAction StopAction = new AppAction("Stop", Utils.newImageIcon("buttons/Stop24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.stopAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.compilationView) && (getView().getDoc().executing));
        }
    };
    AppAction StopAllAction = new AppAction("Stop All", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.stopAllAction();
        }

        public boolean canAct() {
            if (Editor.objectHelpMode) {
                return true;
            }
            if (super.canAct()) {
                Iterator localIterator = Editor.topGrafcharts.getStorage().iterator();
                while (localIterator.hasNext()) {
                    GCDocument localGCDocument = (GCDocument) localIterator.next();
                    if (localGCDocument.executing) {
                        return true;
                    }
                }
            }
            return false;
        }
    };
    AppAction ShutDownSocketAction = new AppAction("Shut Down Socket", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.shutDownAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.topLevelView) && (getView().getDoc().isSocketActive()) && (!getView().getDoc().executing));
        }
    };
    AppAction DebugStepAction = new AppAction("Step", Utils.newImageIcon("buttons/Step24.png"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.debugStepAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.topLevelView) && (getView().getDoc().executing) && (getView().getDoc().paused));
        }
    };
    AppAction DebugPauseAction = new AppAction("Pause", Utils.newImageIcon("buttons/Pause24.png"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.debugPauseAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.topLevelView) && (!getView().getDoc().paused));
        }
    };
    AppAction DebugResumeAction = new AppAction("Resume", Utils.newImageIcon("buttons/Resume24.png"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.debugResumeAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.topLevelView) && (getView().getDoc().paused));
        }
    };
    AppAction HidePaletteAction = new AppAction("Show Palette", this) {
        public boolean canAct() {
            return true;
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.hidePaletteAction();
        }
    };
    AppAction ShowPaletteAction = new AppAction("Show Palette", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || (Editor.paletteHidden);
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
        }
    };
    AppAction TreeViewAction = new AppAction("Tree View", Utils.newImageIcon("buttons/JTree.gif"), this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.myCurrentView != null));
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.treeViewAction();
        }
    };
    AppAction HideToolbarAction = new AppAction("Show Toolbar", this) {
        public boolean canAct() {
            return true;
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.hideToolbarAction();
        }
    };
    AppAction ShowToolbarAction = new AppAction("Show Toolbar", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || (Editor.this.toolbarHidden);
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.showToolbarAction();
        }
    };
    AppAction NewAction = new AppAction("New", Utils.newImageIcon("buttons/New24.gif"), this) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.newAction();
        }

        @Override
        public boolean canAct() {
            return true;
        }
    };
    AppAction OpenAction = new AppAction("Open...", Utils.newImageIcon("buttons/Open24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.openAction();
        }

        public boolean canAct() {
            return true;
        }
    };
    AppAction PrintAction = new AppAction("Print...", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_FileMenu_Print");
            } else {
                getView().print();
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || (super.canAct());
        }
    };
    AppAction SaveAction = new AppAction("Save", Utils.newImageIcon("buttons/Save24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.saveAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.compilationView) && (Editor.myCurrentView != null) && (!getView().getDoc().executing));
        }
    };
    AppAction SaveAsAction = new AppAction("Save As...", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.saveAsAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.compilationView) && (!getView().getDoc().executing));
        }
    };
    AppAction SaveAllAction = new AppAction("SaveAll", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.saveAllAction();
        }

        public boolean canAct() {
            return Editor.this.saveAllCanAct(super.canAct());
        }
    };
    AppAction SaveAllAsAction = new AppAction("SaveAllAs...", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.saveAllAsAction();
        }

        public boolean canAct() {
            return Editor.this.saveAllCanAct(super.canAct());
        }
    };
    AppAction OpenAllAction = new AppAction("OpenAll...", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.openAllAction();
        }

        public boolean canAct() {
            return true;
        }
    };
    AppAction AssistantAction = new AppAction("Assistant", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.assistantAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.topLevelView));
        }
    };
    AppAction PropertiesAction = new AppAction("Application Properties", Utils.newImageIcon("buttons/Properties24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.propertiesAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.topLevelView));
        }
    };
    AppAction CloseAction = new AppAction("Close", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_FileMenu_Close");
            } else if (Editor.topLevelView) {
                Editor.this.closeWorkspaceAction();
            } else {
                GrafcetObject localGrafcetObject = getView().getDoc().getOwner();
                if ((localGrafcetObject instanceof Hierarchical)) {
                    ((Hierarchical) localGrafcetObject).hideWorkspace();
                } else {
                    Utils.writeInternalError("The current view is not an instance of Hierarchical.");
                }
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((!Editor.topLevelView) || (!getView().getDoc().executing)));
        }
    };
    AppAction GridAction = new AppAction("Workspace Properties", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.gridAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || (super.canAct());
        }
    };
    AppAction ExitAction = new AppAction("Exit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.exitAction();
        }

        public boolean canAct() {
            return true;
        }
    };
    AppAction MoveToFrontAction = new AppAction("Move to Front", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.moveToFrontAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (!getView().getSelection().isEmpty()) && (getView().getDoc().isModifiable()));
        }
    };
    AppAction MoveToBackAction = new AppAction("Move to Back", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.moveToBackAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (!getView().getSelection().isEmpty()) && (getView().getDoc().isModifiable()));
        }
    };
    AppAction ZoomOutAction = new AppAction("Zoom Out", Utils.newImageIcon("buttons/ZoomOut24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_ViewMenu_ZoomOut");
            } else {
                Editor.this.zoomOutAction();
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (getView().getScale() > 0.12999999523162842D));
        }
    };
    AppAction ZoomInAction = new AppAction("Zoom In", Utils.newImageIcon("buttons/ZoomIn24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_ViewMenu_ZoomIn");
            } else {
                Editor.this.zoomInAction();
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (getView().getScale() < 8.0D));
        }
    };
    AppAction ZoomResetAction = new AppAction("Zoom Reset", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || (super.canAct());
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.zoomResetAction();
        }
    };
    AppAction ZoomToFitAction = new AppAction("Zoom to Fit", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || (super.canAct());
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.zoomToFitAction();
        }
    };
    AppAction ZoomCutAction = new AppAction("Cut", Utils.newImageIcon("buttons/Cut24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.cutAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.myCurrentView != null) && (!getView().getSelection().isEmpty()) && (!getView().getDoc().executing));
        }
    };
    AppAction ZoomCopyAction = new AppAction("Copy", Utils.newImageIcon("buttons/Copy24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.copyAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.myCurrentView != null) && (!getView().getSelection().isEmpty()));
        }
    };
    AppAction ZoomPasteAction = new AppAction("Paste", Utils.newImageIcon("buttons/Paste24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.pasteAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (!getView().getDoc().executing));
        }
    };
    AppAction GroupAction = new AppAction("Group", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.groupAction();
        }

        public boolean canAct() {
            if (Editor.objectHelpMode) {
                return true;
            }
            if ((super.canAct()) && (!getView().getDoc().executing)) {
                JGoSelection localJGoSelection = getView().getSelection();
                if (localJGoSelection.getNumObjects() >= 2) {
                    JGoListPosition localJGoListPosition = localJGoSelection.getFirstObjectPos();
                    while (localJGoListPosition != null) {
                        JGoObject localJGoObject = localJGoSelection.getObjectAtPos(localJGoListPosition);
                        localJGoListPosition = localJGoSelection.getNextObjectPos(localJGoListPosition);
                        if (!localJGoObject.isTopLevel()) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    };
    AppAction ToggleObjectifyAction = new AppAction("Toggle Objectify", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_EditMenu_ToggleObjectify");
            } else {
                Editor.this.toggleObjectifyAction();
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (getView().getSelection().getNumObjects() == 1) && ((getView().getSelection().getPrimarySelection() instanceof GCGroup)) && (getView().getSelection().getPrimarySelection().isTopLevel()));
        }
    };
    AppAction ToggleBorderAction = new AppAction("Toggle Border", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_EditMenu_ToggleBorder");
            } else {
                Editor.myCurrentView.getDocument().startTransaction();
                ((GCGroup) getView().getSelection().getPrimarySelection()).toggleBorder();
                Editor.myCurrentView.getDocument().endTransaction(Editor.this.ToggleBorderAction.toString());
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (getView().getSelection().getNumObjects() == 1) && ((getView().getSelection().getPrimarySelection() instanceof GCGroup)) && (!((GCGroup) getView().getSelection().getPrimarySelection()).isObjectified()));
        }
    };
    AppAction UngroupAction = new AppAction("Ungroup", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.ungroupAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (getView().getSelection().getNumObjects() == 1) && ((getView().getSelection().getPrimarySelection() instanceof GCGroup)) && (getView().getSelection().getPrimarySelection().isTopLevel()) && (!getView().getDoc().executing));
        }
    };
    AppAction DeleteAction = new AppAction("Delete", Utils.newImageIcon("buttons/Delete24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_EditMenu_Delete");
            } else {
                getView().deleteSelectionSpecial();
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (!getView().getSelection().isEmpty()) && (getView().getDoc().isModifiable()) && (!getView().getDoc().executing));
        }
    };
    AppAction SelectAllAction = new AppAction("Select All", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || (super.canAct());
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_EditMenu_SelectAll");
            } else {
                getView().selectAll();
            }
        }
    };
    AppAction EnableSlideModeAction = new AppAction("Enable Slide Mode", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.enableSlideAction();
            Editor.updateActions();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || (!Editor.slideMode);
        }
    };
    AppAction DisableSlideModeAction = new AppAction("Disable Slide Mode", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.disableSlideAction();
            Editor.updateActions();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || (Editor.slideMode);
        }
    };
    AppAction DPWSServicesAction = new AppAction("DPWS Services", this) {
        public boolean canAct() {
            return Editor.this.dpwsServicesDialog == null;
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (!Editor.objectHelp("Editor_MiscMenu_DPWSServices")) {
                Editor.this.dpwsServicesAction();
            }
        }
    };
    AppAction ConnectCCOMAction = new AppAction("Connect CCOM", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || ((!Editor.serverIP.equals("")) && (!Editor.connectedToBlasterServer));
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (!Editor.objectHelp("Editor_MiscMenu_ConnectCCOM")) {
                Editor.initBlasterAction();
            }
        }
    };
    AppAction CCOMServerAction = new AppAction("CCOM Server Settings", this) {
        public boolean canAct() {
            return true;
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (!Editor.objectHelp("Editor_MiscMenu_CCOMServer")) {
                Editor.this.blasterServerAction();
            }
        }
    };
    AppAction DisconnectCCOMAction = new AppAction("Disconnect CCOM", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || (Editor.connectedToBlasterServer);
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (!Editor.objectHelp("Editor_MiscMenu_DisconnectCCOM")) {
                Editor.logoutBlasterAction();
            }
        }
    };
    AppAction TutorialAction = new AppAction("Open Online Tutorial", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.tutorialAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || (!Editor.this.tutorialOpen);
        }
    };
    AppAction CloseTutorialAction = new AppAction("Close Online Tutorial", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.closeTutorialAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || (Editor.this.tutorialOpen);
        }
    };
    AppAction AboutAction = new AppAction("About", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.aboutAction();
        }

        public boolean canAct() {
            return true;
        }
    };
    AppAction ModeStandardAction = new AppAction("Standard Mode", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.modeStandardAction();
        }

        public boolean canAct() {
            return true;
        }
    };
    AppAction ModeSimulationAction = new AppAction("Simulation Mode", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.modeSimulationAction();
        }

        public boolean canAct() {
            return false;
        }
    };
    AppAction ConnectArduinoAction = new AppAction("Connect", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.connectArduinoAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode || !(Editor.myBoard.isConnected()));
        }
    };
    AppAction DisconnectArduinoAction = new AppAction("Disconnect", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.disconnectArduinoAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode || Editor.myBoard.isConnected());
        }
    };
    AppAction TeleverserAction = new AppAction("Televerser", Utils.newImageIcon("buttons/arduino.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.televerserAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.compilationView) && (Editor.myCurrentView != null) 
                    && (!getView().getDoc().executing) && (getView().getDoc().compiledOK) && (Editor.myBoard.isConnected()));
        }
    };
    
    AppAction ExportArduinCodeAction = new AppAction("Export Arduino source", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.exportArduinCodeAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.compilationView) && (Editor.myCurrentView != null) 
                    && (!getView().getDoc().executing) && (getView().getDoc().compiledOK));
        }
    };
    
    AppAction VariablesEditorAction = new AppAction("Variables", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.variablesEditorAction(VariableEditorDialog.STD_VARIABLE_TAB);
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (Editor.compilationView) && (Editor.myCurrentView != null) && (!getView().getDoc().executing));
        }
    };
    JMenuItem UndoMenuItem = null;
    AppAction UndoAction = new AppAction("Undo", Utils.newImageIcon("buttons/Undo24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_EditMenu_Undo");
            } else {
                getView().getDocument().undo();
                AppAction.updateAllActions();
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((Editor.enableUndo) && (super.canAct()) && (getView().getDocument().canUndo()) && (!getView().getDoc().executing));
        }

        public void updateEnabled() {
            super.updateEnabled();
            if ((Editor.this.UndoMenuItem != null) && (getView() != null)) {
                Editor.this.UndoMenuItem.setText(getView().getDocument().getUndoManager().getUndoPresentationName());
            }
        }
    };
    JMenuItem RedoMenuItem = null;
    AppAction RedoAction = new AppAction("Redo", Utils.newImageIcon("buttons/Redo24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_EditMenu_Redo");
            } else {
                getView().getDocument().redo();
                AppAction.updateAllActions();
            }
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((Editor.enableUndo) && (super.canAct()) && (getView().getDocument().canRedo()) && (!getView().getDoc().executing));
        }

        public void updateEnabled() {
            super.updateEnabled();
            if ((Editor.this.RedoMenuItem != null) && (getView() != null)) {
                Editor.this.RedoMenuItem.setText(getView().getDocument().getUndoManager().getRedoPresentationName());
            }
        }
    };
    AppAction SampleInteractivePIDBasicAction = new AppAction("Interactive PID Basic", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (!Editor.objectHelp("Editor_SamplesMenu_InteractivePIDBasic")) {
                Editor.this.openWorkspace("../ControlLib.xml");
                GCDocument localGCDocument = Editor.this.openWorkspace("Samples/InteractivePIDBasic.xml");
                Editor.this.compileAction();
                Editor.this.executeAction();
                for (JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition)) {
                    JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition);
                    if ((localJGoObject instanceof SFCPlotter)) {
                        Editor.this.selectedObject = localJGoObject;
                        Editor.myCurrentView = localGCDocument.getView();
                        Editor.this.plotterShowAction();
                    }
                }
            }
        }

        public boolean canAct() {
            return true;
        }
    };
    AppAction OnlineHelpAction = new AppAction("Online Help", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (!Editor.objectHelp("Editor_HelpMenu_OnlineHelp")) {
                new CSH.DisplayHelpFromSource(Editor.hb).actionPerformed(paramAnonymousActionEvent);
            }
        }

        public boolean canAct() {
            return true;
        }
    };
    AppAction ObjectHelpAction = new AppAction("Object Help", Utils.newImageIcon("buttons/About24.gif"), this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            if (Editor.objectHelpMode) {
                Editor.objectHelp("Editor_HelpMenu_ObjectHelp");
            } else {
                Editor.this.objectHelpAction();
            }
        }

        public boolean canAct() {
            return true;
        }
    };
    AppAction ShowActionBlockAction = new AppAction("Show Action Block", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.showActionBlockAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((getApp().selectedObject instanceof GCStep)) && (!((GCStep) getApp().selectedObject).actionBlockVisible));
        }
    };
    AppAction HideActionBlockAction = new AppAction("Hide Action Block", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.hideActionBlockAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((getApp().selectedObject instanceof GCStep)) && (((GCStep) getApp().selectedObject).actionBlockVisible));
        }
    };
    AppAction ShowTransitionConditionAction = new AppAction("Show Condition", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.showTransitionConditionAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((getApp().selectedObject instanceof GenericTransition)) && (!((GenericTransition) getApp().selectedObject).conditionVisible));
        }
    };
    AppAction HideTransitionConditionAction = new AppAction("Hide Condition", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.hideTransitionConditionAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((getApp().selectedObject instanceof GenericTransition)) && (((GenericTransition) getApp().selectedObject).conditionVisible));
        }
    };
    AppAction TransitionEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.transitionEditAction();
        }
    };
    AppAction TransitionForceAction = new AppAction("Force", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.transitionForceAction();
        }

        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && (getView().getDoc().executing));
        }
    };
    AppAction StepEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.stepEditAction();
        }
    };
    AppAction SetStepNameAction = new AppAction("Set Name", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.stepSetNameAction();
        }
    };
    AppAction ProcedureStepEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.procedureStepEditAction();
        }
    };
    AppAction ProcedureStepShowAction = new AppAction("Show/Hide Call", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.procedureStepShowAction();
        }

        public boolean canAct() {
            return (super.canAct()) && ((Editor.this.selectedObject instanceof ProcedureStep)) && (((ProcedureStep) Editor.this.selectedObject).myContentDocument != null);
        }
    };
    AppAction ProcessStepShowAction = new AppAction("Calls", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.processStepShowAction();
        }
    };
    AppAction XMLMessageInProcedureAction = new AppAction("Procedure", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.xmlMessageInProcedureAction();
        }
    };
    AppAction GrafcetProcedureStartAction = new AppAction("Start", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.grafcetProcedureStartAction();
        }
    };
    AppAction GrafcetProcedureParametersAction = new AppAction("Parameters", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.grafcetProcedureParametersAction();
        }
    };
    AppAction GrafcetProcedureCallsAction = new AppAction("Calls", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.grafcetProcedureCallsAction();
        }
    };
    AppAction GrafcetProcedureBodyAction = new AppAction("Show/Hide Body", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.grafcetProcedureBodyAction();
        }
    };
    AppAction GrafcetClassBodyAction = new AppAction("Show/Hide Body", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.grafcetClassBodyAction();
        }
    };
    AppAction GrafcetClassInstantiateAction = new AppAction("Instantiate", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.grafcetClassInstantiateAction();
        }
    };
    AppAction GrafcetClassUpdateAction = new AppAction("Update Instances", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.grafcetClassUpdateAction();
        }
    };
    AppAction SetSuperClassAction = new AppAction("Set Superclass", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.setSuperClassAction();
        }
    };
    AppAction MacroStepBodyAction = new AppAction("Show/Hide Body", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.macroStepBodyAction();
        }
    };
    AppAction MacroStepEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.macroStepEditAction();
        }
    };
    AppAction MacroStepSetNameAction = new AppAction("Set Name", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.macroStepSetNameAction();
        }
    };
    AppAction MacroStepStubsAction = new AppAction("Update Stubs", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.macroStepStubsAction();
        }
    };
    AppAction WorkspaceBodyAction = new AppAction("Show/Hide Body", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.workspaceBodyAction();
        }
    };
    AppAction InspectAction = new AppAction("Table", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.inspectAction();
        }
    };
    AppAction CloseByteStreamInAction = new AppAction("Close", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((getApp().selectedObject instanceof ByteStreamIn)) && (((ByteStreamIn) getApp().selectedObject).isConnected()));
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.closeConnectionAction();
        }
    };
    AppAction OpenByteStreamInAction = new AppAction("Open", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((getApp().selectedObject instanceof ByteStreamIn)) && (!((ByteStreamIn) getApp().selectedObject).isConnected()) && (((ByteStreamIn) getApp().selectedObject).isConfigured()));
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.openConnectionAction();
        }
    };
    AppAction ByteStreamInEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.byteStreamInEditAction();
        }
    };
    AppAction CloseByteStreamOutAction = new AppAction("Close", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((getApp().selectedObject instanceof ByteStreamOut)) && (((ByteStreamOut) getApp().selectedObject).isConnected()));
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.closeConnectionOutAction();
        }
    };
    AppAction OpenByteStreamOutAction = new AppAction("Open", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || ((super.canAct()) && ((getApp().selectedObject instanceof ByteStreamOut)) && (!((ByteStreamOut) getApp().selectedObject).isConnected()) && (((ByteStreamOut) getApp().selectedObject).isConfigured()));
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.openConnectionOutAction();
        }
    };
    AppAction ByteStreamOutEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.byteStreamOutEditAction();
        }
    };
    AppAction WorkspaceEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.workspaceEditAction();
        }
    };
    AppAction LabCommObjectEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.labCommObjectEditAction();
        }
    };
    AppAction XMLMessageInBodyAction = new AppAction("Show/Hide Body", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.xmlMessageInBodyAction();
        }
    };
    AppAction XMLMessageInEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.xmlMessageInEditAction();
        }
    };
    AppAction XMLMessageOutBodyAction = new AppAction("Show/Hide Body", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.xmlMessageOutBodyAction();
        }
    };
    AppAction XMLMessageOutEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.xmlMessageOutEditAction();
        }
    };
    AppAction StepFusionSetBodyAction = new AppAction("Show/Hide Body", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.stepFusionSetBodyAction();
        }
    };
    AppAction StepFusionSetEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.stepFusionSetEditAction();
        }
    };
    AppAction PlotterEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.plotterEditAction();
        }
    };
    AppAction PlotterShowAction = new AppAction("Show/Hide Plot", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.plotterShowAction();
        }

        public boolean canAct() {
            return ((Editor.this.selectedObject instanceof SFCPlotter)) && (((SFCPlotter) Editor.this.selectedObject).plotter != null);
        }
    };
    AppAction StepIconAction = new AppAction("Icon", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.stepIconAction();
        }

        public boolean canAct() {
            return Editor.graphicalSteps;
        }
    };
    AppAction DrawableEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.drawableEditAction();
        }
    };
    AppAction DrawableNameAction = new AppAction("Name", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.drawableNameAction();
        }
    };
    AppAction BrowserNameAction = new AppAction("Name", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.browserNameAction();
        }
    };
    AppAction BrowserEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.browserEditAction();
        }
    };
    AppAction IconEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.iconEditAction();
        }
    };
    AppAction IconNameAction = new AppAction("Name", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.iconNameAction();
        }
    };
    AppAction TextEditAction = new AppAction("Edit", this) {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.textEditAction();
        }
    };
    AppAction OverviewAction = new AppAction("Overview", this) {
        public boolean canAct() {
            return (Editor.objectHelpMode) || (super.canAct());
        }

        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            Editor.this.overviewAction();
        }
    };
    static JComboBox messageBox = new JComboBox();
    static boolean generateWarnings = true;
    public static boolean warningAboutNameConflicts = false;
    public static boolean printWarnings = false;
    public boolean tutorialOpen = false;
    public GCView savedView = null;
    public GCView tutorialView = null;
    public boolean savedPaletteState;
    public TreeDisplay treeDisplay = null;
    public boolean treeShown = false;
    public static boolean graphicalSteps = false;
    public static boolean labVersion = false;
    private static boolean enableUndo = false;
    public static boolean debugMode = false;
    public static boolean slideMode = false;
    private static CCOM ccom;
    public static String loginName = "";
    public static String serverIP = "";
    public static String port = "";
    public static boolean connectedToBlasterServer = false;
    private Services dpwsServices = null;
    private ServicesDialog dpwsServicesDialog = null;
    private String dpwsDiscoveryInterface = null;
    public static int pubCount = 1;
    /**
     * Container princilal devant contenir les élément de la fenètre
     */
    public static Container contentPane = null;
    private static MyJDesktopPane myDesktop;
    public static GCView myCurrentView;
    public static GrafcetObject myCurrentObject = null;
    private GCPalette myPaletteFC;
    private GCPalette myPaletteVar;
    private GCPalette myPaletteGUI;
    private GCPalette myPaletteIO;
    private GCPalette myPaletteXML;
    private static MyJTabbedPane tabbedPane;
    private JGoButton drawStrokeButton;
    private JGoButton drawSplineButton;
    protected MyJMenuBar mainMenuBar = new MyJMenuBar();
    protected MyJMenu filemenu = new MyJMenu();
    protected MyJMenu editmenu = new MyJMenu();
    protected MyJMenu viewmenu = new MyJMenu();
    protected MyJMenu executemenu = new MyJMenu();
    protected MyJMenu miscmenu = new MyJMenu();
    protected MyJMenu automatemenu = new MyJMenu();
    protected MyJMenu samplesMenu = new MyJMenu();
    protected MyJMenu helpmenu = new MyJMenu();
    protected MyJToolBar toolBar = new MyJToolBar();
    protected MyJPopupMenu contextStep = new MyJPopupMenu();
    protected MyJPopupMenu contextGCClas = new MyJPopupMenu();
    protected MyJPopupMenu contextTransition = new MyJPopupMenu();
    protected MyJPopupMenu contextByteStreamIn = new MyJPopupMenu();
    protected MyJPopupMenu contextByteStreamOut = new MyJPopupMenu();
    protected MyJPopupMenu contextProcedureStep = new MyJPopupMenu();
    protected MyJPopupMenu contextProcessStep = new MyJPopupMenu();
    protected MyJPopupMenu contextMacroStep = new MyJPopupMenu();
    protected MyJPopupMenu contextWorkspaceObject = new MyJPopupMenu();
    protected MyJPopupMenu contextLabCommObject = new MyJPopupMenu();
    protected MyJPopupMenu contextXMLMessageIn = new MyJPopupMenu();
    protected MyJPopupMenu contextXMLMessageOut = new MyJPopupMenu();
    protected MyJPopupMenu contextStepFusionSet = new MyJPopupMenu();
    protected MyJPopupMenu contextProcedure = new MyJPopupMenu();
    protected MyJPopupMenu contextSFCPlotter = new MyJPopupMenu();
    protected MyJPopupMenu contextDrawable = new MyJPopupMenu();
    protected MyJPopupMenu contextBrowser = new MyJPopupMenu();
    protected MyJPopupMenu contextIcon = new MyJPopupMenu();
    protected MyJPopupMenu contextText = new MyJPopupMenu();
    MyJPanel messageListPanel = new MyJPanel();
    public Cursor helpCursor;
    public Image helpImage;
    private static boolean paletteHidden = false;
    private boolean toolbarHidden = false;
    public JGoObject selectedObject = null;
    public static Color red = new Color(1.0F, 0.0F, 0.0F);
    public static Color black = new Color(0.0F, 0.0F, 0.0F);
    private int myDocCount = 1;
    public static boolean topLevelView = false;
    public static boolean compilationView = false;
    /**
     * Unique instance de Editor en cours.
     */
    public static Editor singleton = null;
    public static GrafchartStorage topGrafcharts = new GrafchartStorage();
    private String writeAllFileLocation = "";
    public HelpSet hs = null;
    public static DefaultHelpBroker hb = null;
    public static LocalIO localIO = null;
    protected MyJFrame myOverviewWindow;
    protected Overview myOverview;
    public static ArduinoBoard myBoard = new ArduinoBoard(null);
    /**
     * Défini si Object Help est activé ou non, afin de savoir si oui ou non l'action
     * doit etre exécuter ou le fenètre d'aide affichée.
     */
    protected static boolean objectHelpMode = false;
    static int messageBoxIndex = 0;
    static int errorCount = 0;
    static int warningCount = 0;

    public static void testSetup() {
        isTest = true;
        singleton = new Editor(new String[0]);
        myCurrentView = new GCView(new GCDocument());
    }

    public Editor(String[] paramArrayOfString) {
        contentPane = getContentPane();
        contentPane.setBackground(new Color(0.8F, 0.8F, 0.8F));
        String str1 = "";
        if (!loginName.equals("JGrafchart")) {
            str1 = str1 + loginName + " - ";
        }
        
        // le nom de la fenètre principale précédé du nom de l'utiliqteur connecté
        //str1 = str1 + "JGrafchart 2.6.1";
        str1 = str1 + "JGrafchart - Arduino Beta";
        setTitle(str1);
        Utils.newFile(getFileNotFoundImagePath());
        
        /** Configuration de la fenètre d'aide **/
        try {
            File localFile = Utils.newFile("../doc/help/myHelpSet.hs");
            URL localURL = localFile.toURI().toURL();
            if (localURL == null) {
                Utils.writeInternalError("hsURL is null");
                System.exit(1);
            }
            this.hs = new HelpSet(null, localURL);
        } catch (MalformedURLException | HelpSetException localException1) {
            Utils.writeException(localException1, "HelpSet file not found");
            System.exit(1);
        }
        hb = (DefaultHelpBroker) this.hs.createHelpBroker();
        hb.setFont(new Font(hb.getFont().getName(), hb.getFont().getStyle(), 14));
        hb.getWindowPresentation().getHelpWindow().setIconImage(Utils.newImageIcon("JGrafchartIcon.png").getImage());
        
        /** Dimension de la fenetre **/
        int i = 1024;
        int j = 768;
        String str2 = i + "x" + j;
        String geometryStr = getArg(paramArrayOfString, "-geometry", str2);
        int dimW = i;
        int dimH = j;
        try {
            int n = geometryStr.indexOf('x');
            String localObject = geometryStr.substring(0, n);
            String str4 = geometryStr.substring(n + 1);
            dimW = Integer.parseInt((String) localObject);
            dimH = Integer.parseInt(str4);
        } catch (Exception localException2) {
            Utils.writeError("The specified -geometry is invalid (" + geometryStr + "), using default (" + str2 + ").");
        }
        //Récupération des tailles de l'écran
        Rectangle screenRect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        dimW = Math.min(dimW, screenRect.width);
        dimH = Math.min(dimH, screenRect.height);
        setSize(dimW, dimH);
        //Centre la fenètre au milieu de l'ecran
        setLocation(screenRect.x + (screenRect.width - dimW) / 2, screenRect.y + (screenRect.height - dimH) / 2);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //Ne pas directement fermer la fenètre
        addWindowListener(new MyWindowAdapter() {      //Mais plutot capturer l'evenement
            @Override
            public void windowClosing(WindowEvent paramAnonymousWindowEvent) {
                Editor.objectHelpMode = false;
                Editor.this.exitAction();              //Et déclencher la method exitAction
            }
            
        });
        
        //initialisation des menu, de la barre d'acces rapide et des menus contextuels 
        initMenus();
        initPopupMenus();
        
        //Gestion de la pallete
        this.myPaletteFC = new GCPalette(this, "Function Chart");
        this.myPaletteVar = new GCPalette(this, "Variables");
        this.myPaletteIO = new GCPalette(this, "Input/Output");
        this.myPaletteXML = new GCPalette(this, "XML");
        this.myPaletteGUI = new GCPalette(this, "Graphics");
        tabbedPane = new MyJTabbedPane();
        tabbedPane.addTab("FC", this.myPaletteFC);
        tabbedPane.setSelectedIndex(0);
        //tabbedPane.addTab("Var", this.myPaletteVar);
        //tabbedPane.addTab("IO ", this.myPaletteIO);
        if (!labVersion) {
            //tabbedPane.addTab("XML", this.myPaletteXML);
        }
        tabbedPane.addTab("GUI", this.myPaletteGUI);
        
        myDesktop = new MyJDesktopPane();
        this.toolBar.setFloatable(false);
        this.messageListPanel.setLayout(new BoxLayout(this.messageListPanel, BoxLayout.X_AXIS));
        this.messageListPanel.add(new JLabel(" Messages: "));
        this.messageListPanel.add(messageBox);
        
        //les messages et et JDesktop sont combinés dans desktopBloc
        MyJPanel desktopBloc = new MyJPanel();
        desktopBloc.setLayout(new BoxLayout(desktopBloc, BoxLayout.Y_AXIS));
        desktopBloc.add(myDesktop);
        //desktopBloc.add(this.messageListPanel);
        
        
        //Constitution de l'interface principale
        contentPane.setLayout(new MyBorderLayout());
        contentPane.add(this.toolBar, BorderLayout.NORTH);
        contentPane.add(desktopBloc, "Center");
        contentPane.add(tabbedPane, "West");
        contentPane.add(messageListPanel, BorderLayout.SOUTH);
        contentPane.validate(); //Valide l'agencement
        
        if (!isTest) {
            setVisible(true); //Affichage de l'interface en mode no test
        }
        
        this.myPaletteFC.initializeDragDropHandling();
        this.myPaletteVar.initializeDragDropHandling();
        this.myPaletteIO.initializeDragDropHandling();
        this.myPaletteXML.initializeDragDropHandling();
        this.myPaletteGUI.initializeDragDropHandling();
        initPaletteFC();
        initPaletteVar();
        initPaletteIO();
        if (!labVersion) {
            initPaletteXML();
        }
        initPaletteGUI();
        try {
            this.helpImage = getToolkit().createImage(Utils.newFile("../iconlib/help.png").getPath());
            this.helpCursor = getToolkit().createCustomCursor(this.helpImage, new Point(22, 24), "Help Cursor");
        } catch (Exception localException3) {
        }
        this.dpwsDiscoveryInterface = getArg(paramArrayOfString, "--dpwsDiscovery", null);
        if (!labVersion) {
            getDPWSServices();
        }
    }

    /**
     * Retourne le repèretoire des icones et autres images
     * @return 
     */
    public static String getIconLib() {
        return "../iconlib";
    }

    /**
     * Image par defaut 
     * @return le path de l'image par défaut
     */
    public static String getFileNotFoundImagePath() {
        return getIconLib() + "/FileNotFound.png";
    }

    /**
     * Lance la fenètre d'aide si on est en mode Object Help.
     * @param idString ID de l'aide demandée
     * @return true si l'ID existe et que tout s'est bien passé et false sinon ou qu'on 
     * n'est pas en mode Object Help.
     */
    public static boolean objectHelp(String idString) {
        return objectHelp(idString, false);
    }

    /**
     * Lance la fenètre d'aide si on est en mode Object Help, ou si expressement démandé.
     * @param idString ID de l'aide demandée
     * @param force si true ouvre l'aide sinon c'est le mode Object Help qui en décide
     * @return true si l'ID existe et que tout s'est bien passé et false si toutes les 
     * contions ne sont pas remplies.
     */
    public static boolean objectHelp(String idString, boolean force) {
        if ((objectHelpMode) || (force)) {
            try {
                hb.setDisplayed(true);
                hb.setCurrentID(idString);
                if (!force) {
                    singleton.objectHelpAction();
                }
            } catch (BadIDException localBadIDException) {
                Utils.writeInternalError("Invalid map id \"" + idString + "\".");
            } catch (Exception localException) {
                Utils.writeException(localException);
            }
            return true;
        }
        return false;
    }

    private boolean saveAllCanAct(boolean paramBoolean) {
        if (objectHelpMode) {
            return true;
        }
        if (paramBoolean) {
            Iterator localIterator = topGrafcharts.getStorage().iterator();
            while (localIterator.hasNext()) {
                GCDocument localGCDocument = (GCDocument) localIterator.next();
                if (localGCDocument.executing) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Initialisation de la bar des menus et de la bare d'actions rapides
     */
    void initMenus() {
        JMenuItem localJMenuItem;
        this.mainMenuBar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F10"), "none");
        /** Menu file **/
        this.filemenu.setText("File");
        this.filemenu.setMnemonic(KeyEvent.VK_F); //ALT+N si il est visible
        //Ajoute le sous menu New avec l'action NewAction, la combinaison de touche CTRL+N, et ALT+N si il est visible
        this.filemenu.add(this.NewAction, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK), KeyEvent.VK_N);
        this.filemenu.add(this.OpenAction, KeyStroke.getKeyStroke(79, 2), 79);
        localJMenuItem = this.filemenu.add(this.CloseAction, KeyStroke.getKeyStroke(115, 2), 67);
        localJMenuItem.getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 2), "Close");
        localJMenuItem.getActionMap().put("Close", this.CloseAction);
        this.filemenu.add(this.SaveAction, KeyStroke.getKeyStroke(83, 2), 83);
        localJMenuItem = this.filemenu.add(this.SaveAsAction, KeyStroke.getKeyStroke(123, 0), 65);
        localJMenuItem.setDisplayedMnemonicIndex(5);
        this.filemenu.addSeparator();
        //this.filemenu.add(this.OpenAllAction, 76);
        this.filemenu.add(this.SaveAllAction, 86);
        //this.filemenu.add(this.SaveAllAsAction, 69);
        this.filemenu.addSeparator();
        MyJMenu recentMenu = new MyJMenu();
        recentMenu.setText("Open Recent File");
        recentMenu.setMnemonic(KeyEvent.VK_F);
        recentMenu.setDisplayedMnemonicIndex(12);
        generateRecentFiles(recentMenu, 5);
        this.filemenu.add(recentMenu);
        this.filemenu.addSeparator();
        this.filemenu.add(this.PrintAction, KeyStroke.getKeyStroke(80, 2), 80);
        this.filemenu.addSeparator();
        this.filemenu.add(this.ExitAction, KeyStroke.getKeyStroke(115, 8), 88);
        this.mainMenuBar.add(this.filemenu);
        
        /** Menu Edit **/
        this.editmenu.setText("Edit");        
        this.editmenu.setMnemonic(69);
        if (enableUndo) {
            this.UndoMenuItem = this.editmenu.add(this.UndoAction, KeyStroke.getKeyStroke(90, 2), 85);
            this.RedoMenuItem = this.editmenu.add(this.RedoAction, KeyStroke.getKeyStroke(89, 2), 82);
            this.RedoMenuItem.getInputMap(2).put(KeyStroke.getKeyStroke(90, 3), "Redo");
            this.RedoMenuItem.getActionMap().put("Redo", this.RedoAction);
            this.editmenu.addSeparator();
        }
        this.editmenu.add(this.ZoomCutAction, KeyStroke.getKeyStroke(88, 2), 84);
        this.editmenu.add(this.ZoomCopyAction, KeyStroke.getKeyStroke(67, 2), 67);
        this.editmenu.add(this.ZoomPasteAction, KeyStroke.getKeyStroke(86, 2), 80);
        this.editmenu.add(this.DeleteAction, 68);
        this.editmenu.addSeparator();
        this.editmenu.add(this.SelectAllAction, KeyStroke.getKeyStroke(65, 2), 65);
        this.editmenu.addSeparator();
        this.editmenu.add(this.AssistantAction, KeyEvent.VK_S);
        this.editmenu.addSeparator();
        this.editmenu.add(this.PropertiesAction, KeyStroke.getKeyStroke(10, 8), 69);
        //this.editmenu.add(this.GridAction, KeyStroke.getKeyStroke(71, 2), 71);
        this.editmenu.addSeparator();
        this.editmenu.add(this.GroupAction, 79);
        //this.editmenu.add(this.ToggleObjectifyAction, 74);
        this.editmenu.add(this.ToggleBorderAction, 76);
        this.editmenu.add(this.UngroupAction, 78);
        this.editmenu.addSeparator();
        this.editmenu.add(this.MoveToFrontAction, KeyStroke.getKeyStroke(70, 2), 70);
        this.editmenu.add(this.MoveToBackAction, KeyStroke.getKeyStroke(66, 2), 66);
        this.mainMenuBar.add(this.editmenu);
        
        /** Menu View **/
        this.viewmenu.setText("View");
        this.viewmenu.setMnemonic(86);
        localJMenuItem = this.viewmenu.add(this.ZoomInAction, KeyStroke.getKeyStroke(107, 2), 73);
        localJMenuItem.getInputMap(2).put(KeyStroke.getKeyStroke('+'), "ZoomIn");
        // Zoom sous l'action de la souris
        localJMenuItem.getActionMap().put("ZoomIn", new AbstractAction() {
            public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
                Utils.writeDebug("Zoom+");
                if ((paramAnonymousActionEvent.getModifiers() & 0x2) != 0) {
                    Editor.this.ZoomInAction.actionPerformed(paramAnonymousActionEvent);
                }
            }
        });
        localJMenuItem = this.viewmenu.add(this.ZoomOutAction, KeyStroke.getKeyStroke(109, 2), 79);
        localJMenuItem.setDisplayedMnemonicIndex(5);
        localJMenuItem.getInputMap(2).put(KeyStroke.getKeyStroke('-'), "ZoomOut");
        localJMenuItem.getActionMap().put("ZoomOut", new AbstractAction() {
            public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
                Utils.writeDebug("Zoom-");
                if ((paramAnonymousActionEvent.getModifiers() & 0x2) != 0) {
                    Editor.this.ZoomOutAction.actionPerformed(paramAnonymousActionEvent);
                }
            }
        });
        this.viewmenu.add(this.ZoomResetAction, KeyStroke.getKeyStroke(96, 2), 82);
        this.viewmenu.registerKeyboardAction(this.ZoomResetAction, KeyStroke.getKeyStroke(48, 2), 2);
        this.viewmenu.add(this.ZoomToFitAction, 70);
        this.viewmenu.addSeparator();        
        //this.viewmenu.add(this.TreeViewAction, 84);
        this.viewmenu.add(this.OverviewAction, 86);
        this.viewmenu.addSeparator(); 
        JCheckBoxMenuItem jCheckBoxMenuItem; 
        jCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem(this.HidePaletteAction);
        jCheckBoxMenuItem.setSelected(true);
        jCheckBoxMenuItem.setMnemonic(80);
        this.viewmenu.add(jCheckBoxMenuItem);
        //this.viewmenu.add(this.ShowPaletteAction, 80);
        //this.viewmenu.add(this.HidePaletteAction, 72);
        this.viewmenu.addSeparator();
        jCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem(this.HideToolbarAction);
        jCheckBoxMenuItem.setSelected(true);
        jCheckBoxMenuItem.setMnemonic(66);
        this.viewmenu.add(jCheckBoxMenuItem);
        //this.viewmenu.add(this.ShowToolbarAction, 66);
        //this.viewmenu.add(this.HideToolbarAction, 68);
        this.mainMenuBar.add(this.viewmenu);
        
        /** Menu Execute **/
        this.executemenu.setText("Execute");
        this.executemenu.setMnemonic(88);
        this.executemenu.add(this.CompileAction, KeyStroke.getKeyStroke(118, 2), 79);
        //this.executemenu.add(this.CompileAllAction, KeyStroke.getKeyStroke(118, 0), 67);
        this.executemenu.addSeparator();
        this.executemenu.add(this.ExecuteAction, KeyStroke.getKeyStroke(116, 2), 88);
        //this.executemenu.add(this.ExecuteAllAction, KeyStroke.getKeyStroke(116, 0), 69);
        this.executemenu.add(this.StopAction, KeyStroke.getKeyStroke(116, 3), 84);
        //this.executemenu.add(this.StopAllAction, KeyStroke.getKeyStroke(116, 1), 83);
        this.executemenu.addSeparator();
        this.executemenu.add(this.DebugStepAction, KeyStroke.getKeyStroke(121, 0), 80);
        this.executemenu.add(this.DebugPauseAction, KeyStroke.getKeyStroke(119, 1), 85);
        this.executemenu.add(this.DebugResumeAction, KeyStroke.getKeyStroke(119, 0), 82);        
        this.executemenu.addSeparator();
        //this.executemenu.add(this.ShutDownSocketAction, KeyStroke.getKeyStroke(117, 0), 68);
        this.mainMenuBar.add(this.executemenu);
        
        /** Menu Misc. **
        this.miscmenu.setText("Misc.");
        this.miscmenu.setMnemonic(77);
        this.miscmenu.add(this.ConnectCCOMAction, 67);
        this.miscmenu.add(this.DisconnectCCOMAction, 68);
        this.miscmenu.add(this.CCOMServerAction, 69);
        this.miscmenu.addSeparator();
        this.miscmenu.add(this.EnableSlideModeAction, 83);
        this.miscmenu.add(this.DisableSlideModeAction, 73);
        this.miscmenu.addSeparator();
        this.miscmenu.add(this.DPWSServicesAction, 87);
        this.mainMenuBar.add(this.miscmenu);
        */
        
        /** Menu Automate **/
        this.automatemenu.setText("Automate");
        this.automatemenu.setMnemonic(KeyEvent.VK_A);
        this.automatemenu.add(ModeStandardAction, KeyEvent.VK_S);
        this.automatemenu.add(ModeSimulationAction, KeyEvent.VK_U);
        this.automatemenu.addSeparator();
        this.automatemenu.add(ConnectArduinoAction, KeyEvent.VK_C);
        this.automatemenu.add(DisconnectArduinoAction, KeyEvent.VK_D);
        this.automatemenu.add(TeleverserAction, KeyEvent.VK_T);
        this.automatemenu.add(ExportArduinCodeAction, KeyEvent.VK_E);
        this.automatemenu.addSeparator();
        this.automatemenu.add(VariablesEditorAction, KeyEvent.VK_V);        
        this.mainMenuBar.add(this.automatemenu);
        
        /** Menu Samples **
        this.samplesMenu.setText("Samples");
        this.samplesMenu.setMnemonic(83);
        this.samplesMenu.add(this.SampleInteractivePIDBasicAction, 80);
        this.mainMenuBar.add(this.samplesMenu);
        */
        
        /** Menu Help **/
        this.helpmenu.setText("Help");
        this.helpmenu.setMnemonic(72);
        this.helpmenu.add(this.OnlineHelpAction, KeyStroke.getKeyStroke(112, 0), 72);
        this.helpmenu.add(this.ObjectHelpAction, 79);
        this.helpmenu.addSeparator();
        this.helpmenu.add(this.TutorialAction, 84);
        this.helpmenu.add(this.CloseTutorialAction, 67);
        this.helpmenu.addSeparator();
        this.helpmenu.add(this.AboutAction, 65);
        this.mainMenuBar.add(this.helpmenu);
        
        setJMenuBar(this.mainMenuBar); //ON attache le menuBar à la fenètre
        
        /** Barre d'actions rapides **/
        this.toolBar.add(this.NewAction).setToolTipText("New");
        this.toolBar.add(this.OpenAction).setToolTipText("Open");
        this.toolBar.add(this.SaveAction).setToolTipText("Save");
        this.toolBar.addSeparator();
        this.toolBar.add(this.PropertiesAction).setToolTipText("Application Properties");
        this.toolBar.addSeparator();
        this.toolBar.add(this.UndoAction).setToolTipText(enableUndo ? "Undo" : "Undo (disabled)");
        this.toolBar.add(this.RedoAction).setToolTipText(enableUndo ? "Redo" : "Redo (disabled)");
        this.toolBar.addSeparator();
        this.toolBar.add(this.ZoomCutAction).setToolTipText("Cut");
        this.toolBar.add(this.ZoomCopyAction).setToolTipText("Copy");
        this.toolBar.add(this.ZoomPasteAction).setToolTipText("Paste");
        this.toolBar.add(this.DeleteAction).setToolTipText("Delete");
        this.toolBar.addSeparator();
        this.toolBar.add(this.CompileAction).setToolTipText("Compile");
        this.toolBar.add(this.ExecuteAction).setToolTipText("Execute");
        this.toolBar.add(this.TeleverserAction).setToolTipText("Televerser");
        this.toolBar.add(this.StopAction).setToolTipText("Stop");
        this.toolBar.addSeparator();
        this.toolBar.add(this.ZoomInAction).setToolTipText("Zoom In");
        this.toolBar.add(this.ZoomOutAction).setToolTipText("Zoom Out");
        //this.toolBar.addSeparator();
        //this.toolBar.add(this.TreeViewAction).setToolTipText("Tree View");
        this.toolBar.addSeparator();
        this.toolBar.add(this.DebugStepAction).setToolTipText("Step");
        this.toolBar.add(this.DebugPauseAction).setToolTipText("Pause");
        this.toolBar.add(this.DebugResumeAction).setToolTipText("Resume");
        this.toolBar.addSeparator();
        this.toolBar.add(this.ObjectHelpAction).setToolTipText("Object Help");
        this.toolBar.addSeparator();
    }

    /**
     * Initialisation des menus contextuel.
     */
    private void initPopupMenus() {
        this.contextStep.setLabel("Step");
        this.contextStep.add(this.ShowActionBlockAction, 83);
        this.contextStep.add(this.HideActionBlockAction, 72);
        this.contextStep.add(this.StepEditAction, 69);
        this.contextStep.add(this.SetStepNameAction, 78);
        if (graphicalSteps) {
            this.contextStep.add(this.StepIconAction, 73);
        }
        
        this.contextGCClas.setLabel("Grafcet Class");
        this.contextGCClas.add(this.GrafcetClassBodyAction, 66);
        this.contextGCClas.add(this.SetSuperClassAction, 83);
        this.contextGCClas.add(this.GrafcetClassInstantiateAction, 73);
        this.contextGCClas.add(this.GrafcetClassUpdateAction, 85);
        
        this.contextByteStreamIn.setLabel("ByteStream In");
        this.contextByteStreamIn.add(this.ByteStreamInEditAction, 69);
        this.contextByteStreamIn.add(this.OpenByteStreamInAction, 79);
        this.contextByteStreamIn.add(this.CloseByteStreamInAction, 67);
        
        this.contextByteStreamOut.setLabel("ByteStream Out");
        this.contextByteStreamOut.add(this.ByteStreamOutEditAction, 69);
        this.contextByteStreamOut.add(this.OpenByteStreamOutAction, 79);
        this.contextByteStreamOut.add(this.CloseByteStreamOutAction, 67);
        
        this.contextTransition.setLabel("Transition");
        this.contextTransition.add(this.ShowTransitionConditionAction, 83);
        this.contextTransition.add(this.HideTransitionConditionAction, 72);
        this.contextTransition.add(this.TransitionEditAction, 69);
        this.contextTransition.add(this.TransitionForceAction, 70);
        
        this.contextProcedureStep.setLabel("Procedure Step");
        this.contextProcedureStep.add(this.ProcedureStepEditAction, 69);
        this.contextProcedureStep.add(this.ProcedureStepShowAction, 67);
        if (graphicalSteps) {
            this.contextProcedureStep.add(this.StepIconAction, 73);
        }
        
        this.contextProcessStep.setLabel("Process Step");
        this.contextProcessStep.add(this.ProcedureStepEditAction, 69);
        this.contextProcessStep.add(this.ProcessStepShowAction, 67);
        if (graphicalSteps) {
            this.contextProcessStep.add(this.StepIconAction, 73);
        }
        
        this.contextMacroStep.setLabel("Macro Step");
        this.contextMacroStep.add(this.MacroStepBodyAction, 66);
        this.contextMacroStep.add(this.InspectAction, 84);
        this.contextMacroStep.add(this.MacroStepEditAction, 69);
        this.contextMacroStep.add(this.MacroStepSetNameAction, 78);
        this.contextMacroStep.add(this.MacroStepStubsAction, 85);
        if (graphicalSteps) {
            this.contextMacroStep.add(this.StepIconAction, 73);
        }
        
        this.contextWorkspaceObject.setLabel("Workspace Object");
        this.contextWorkspaceObject.add(this.WorkspaceBodyAction, 66);
        this.contextWorkspaceObject.add(this.InspectAction, 84);
        this.contextWorkspaceObject.add(this.WorkspaceEditAction, 69);
        
        this.contextLabCommObject.setLabel("LabComm Object");
        this.contextLabCommObject.add(this.LabCommObjectEditAction, 69);
        this.contextLabCommObject.add(this.InspectAction, 84);
        
        this.contextXMLMessageIn.setLabel("XML Message In");
        this.contextXMLMessageIn.add(this.XMLMessageInBodyAction, 66);
        this.contextXMLMessageIn.add(this.InspectAction, 84);
        this.contextXMLMessageIn.add(this.XMLMessageInEditAction, 69);
        this.contextXMLMessageIn.add(this.XMLMessageInProcedureAction, 80);
        this.contextXMLMessageIn.add(this.ProcessStepShowAction, 67);
        
        this.contextXMLMessageOut.setLabel("XML Message Out");
        this.contextXMLMessageOut.add(this.XMLMessageOutBodyAction, 66);
        this.contextXMLMessageOut.add(this.InspectAction, 84);
        this.contextXMLMessageOut.add(this.XMLMessageOutEditAction, 69);
        
        this.contextProcedure.setLabel("Procedure");
        this.contextProcedure.add(this.GrafcetProcedureBodyAction, 66);
        this.contextProcedure.add(this.InspectAction, 84);
        this.contextProcedure.add(this.GrafcetProcedureParametersAction, 80);
        this.contextProcedure.add(this.GrafcetProcedureStartAction, 83);
        this.contextProcedure.add(this.GrafcetProcedureCallsAction, 67);
        
        this.contextStepFusionSet.setLabel("Step Fusion Set");
        this.contextStepFusionSet.add(this.StepFusionSetBodyAction, 66);
        this.contextStepFusionSet.add(this.StepFusionSetEditAction, 69);
        
        this.contextSFCPlotter.add(this.PlotterShowAction, 80);
        this.contextSFCPlotter.add(this.PlotterEditAction, 69);
        
        this.contextDrawable.add(this.DrawableEditAction, 69);
        this.contextDrawable.add(this.DrawableNameAction, 78);
        
        this.contextBrowser.add(this.BrowserEditAction, 69);
        this.contextBrowser.add(this.BrowserNameAction, 78);
        
        this.contextIcon.add(this.IconEditAction, 69);
        this.contextIcon.add(this.IconNameAction, 78);
        
        this.contextText.add(this.TextEditAction, 69);
        this.contextText.add(this.DrawableNameAction, 78);
    }

    /**
     * Method appelée lorqu'on fait l'action Objet Helper
     */
    public void objectHelpAction() {
        objectHelpMode = !objectHelpMode;
        clearMessages();
        if (objectHelpMode) {
            writeMessage("Click on selected object for help.");
            setCursor(this.helpCursor);
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
        updateActions();
    }

    public void closeTutorialAction() {
        if (!objectHelp("Editor_HelpMenu_CloseTutorial")) {
            if (this.tutorialView.getDoc().executing) {
                GCDocument localGCDocument;
                for (localGCDocument = (GCDocument) this.tutorialView.getDocument(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
                    if (((localGCDocument.owner instanceof MacroStep)) && (!(localGCDocument.owner instanceof ProcedureStep))) {
                        ((MacroStep) localGCDocument.owner).parentView.setDragDropEnabled(true);
                    }
                    if ((localGCDocument.owner instanceof WorkspaceObject)) {
                        ((WorkspaceObject) localGCDocument.owner).parentView.setDragDropEnabled(true);
                    }
                    if ((localGCDocument.owner instanceof XMLMessageIn)) {
                        ((XMLMessageIn) localGCDocument.owner).parentView.setDragDropEnabled(true);
                    }
                    if ((localGCDocument.owner instanceof XMLMessageOut)) {
                        ((XMLMessageOut) localGCDocument.owner).parentView.setDragDropEnabled(true);
                    }
                    if ((localGCDocument.owner instanceof GrafcetProcedure)) {
                        ((GrafcetProcedure) localGCDocument.owner).parentView.setDragDropEnabled(true);
                    }
                }
                localGCDocument.stopThread();
                localGCDocument.stopDocument();
                this.tutorialView.setDragDropEnabled(true);
                localGCDocument.setDragAndDrop(true);
                localGCDocument.setSkipsUndoManager(false);
                updateActions();
            }
            this.tutorialOpen = false;
            try {
                this.tutorialView.getInternalFrame().setClosed(true);
            } catch (Exception localException) {
                Utils.writeException(localException);
            }
            myCurrentView = this.savedView;
            updateActions();
            if (!this.savedPaletteState) {
                showPaletteAction();
            }
        }
    }

    public void tutorialAction() {
        if (!objectHelp("Editor_HelpMenu_OpenTutorial")) {
            this.savedView = myCurrentView;
            this.savedPaletteState = paletteHidden;
            this.tutorialOpen = true;
            hidePaletteAction();
            setCursor(Cursor.getPredefinedCursor(3));
            GCDocument localGCDocument = null;
            String str = Utils.newFile("../doc/tutorial/tutorial.xml").getPath();
            Document localDocument = loadXMLDocumentFromFile(str);
            if (localDocument != null) {
                localGCDocument = new GCDocument();
                localGCDocument.setSuspendUpdates(true);
                localGCDocument.loadXML(localDocument.getDocumentElement());
                topGrafcharts.add(localGCDocument);
            }
            localGCDocument.setWriteFileLocation(str);
            localGCDocument.setReadFileLocation(str);
            final GCView localGCView = new GCView(localGCDocument);
            this.tutorialView = localGCView;
            localGCView.layer = 999;
            localGCDocument.setView(localGCView);
            MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localGCDocument.getName(), false);
            localMyJInternalFrame.setDefaultCloseOperation(2);
            localGCView.initialize(this, localMyJInternalFrame);
            localGCView.setScale(localGCDocument.currentScale);
            if (localGCDocument.point != null) {
                localGCView.setViewPosition(localGCDocument.point);
            }
            localMyJInternalFrame.setBounds(localGCDocument.bounds);
            localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = localGCView;
                    Editor.compilationView = true;
                    Editor.topLevelView = true;
                    Editor.myCurrentView.requestFocus();
                    AppAction.updateAllActions();
                }

                public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.this.closeMacros(localGCView.getDoc());
                    Editor.topGrafcharts.remove(localGCView.getDoc());
                    Editor.compilationView = false;
                    Editor.myCurrentView = null;
                    Editor.this.tutorialOpen = false;
                }

                public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.this.closeMacros(localGCView.getDoc());
                    Editor.topGrafcharts.remove(localGCView.getDoc());
                    Editor.compilationView = false;
                    Editor.myCurrentView = null;
                    Editor.this.tutorialOpen = false;
                }

                public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }
            });
            localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
            localMyJInternalFrame.getContentPane().add(localGCView);
            getDesktop().add(localMyJInternalFrame, new Integer(999));
            localMyJInternalFrame.show();
            localGCView.initializeDragDropHandling();
            localGCDocument.setSuspendUpdates(false);
            if (!localGCDocument.modificationProperty) {
                localGCDocument.setModificationProperty(false);
            }
            localGCView.hasHorizontalScrollBar = localGCDocument.horizontalScrollBar;
            if (!localGCDocument.horizontalScrollBar) {
                localGCView.setHorizontalScrollBar(null);
            }
            localGCView.hasVerticalScrollBar = localGCDocument.verticalScrollBar;
            if (!localGCDocument.verticalScrollBar) {
                localGCView.setVerticalScrollBar(null);
            }
            for (localGCDocument = (GCDocument) myCurrentView.getDocument(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
            }
            SymbolTableHelper.warnAboutNameConflicts(localGCDocument);
            setOutHack(localGCDocument, localGCDocument);
            compileDocument(localGCDocument, false);
            compileDocument(localGCDocument, false);
            updateActions();
            localGCDocument.initializeDocument(localGCDocument, localGCDocument.isSimulating());
            myCurrentView.setDragDropEnabled(false);
            localGCDocument.setDragAndDrop(false);
            localGCDocument.start();
            updateActions();
            setCursor(Cursor.getPredefinedCursor(0));
        }
    }

    void overviewAction() {
        if (!objectHelp("Editor_ViewMenu_Overview")) {
            if (this.myOverview == null) {
                this.myOverview = new Overview();
                this.myOverview.setObserved(myCurrentView);
                this.myOverviewWindow = new MyJFrame("Overview");
                this.myOverviewWindow.setLocation(50, 50);
                this.myOverviewWindow.setDefaultCloseOperation(2);
                this.myOverviewWindow.getContentPane().setLayout(new MyBorderLayout());
                this.myOverviewWindow.getContentPane().add(this.myOverview, "Center");
                this.myOverviewWindow.addWindowListener(new MyWindowAdapter() {
                    public void windowClosing(WindowEvent paramAnonymousWindowEvent) {
                        Editor.this.myOverview = null;
                        Editor.this.myOverviewWindow.setVisible(false);
                        Editor.this.myOverviewWindow.dispose();
                    }
                });
            }
            this.myOverviewWindow.pack();
            this.myOverviewWindow.setVisible(true);
        }
    }

    void moveToFrontAction() {
        if (!objectHelp("Editor_EditMenu_MoveToFront")) {
            GCView localGCView = myCurrentView;
            JGoDocument localJGoDocument = myCurrentView.getDocument();
            localJGoDocument.startTransaction();
            JGoSelection localJGoSelection = localGCView.getSelection();
            for (JGoListPosition localJGoListPosition = localJGoSelection.getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = localJGoSelection.getNextObjectPos(localJGoListPosition)) {
                JGoObject localJGoObject = localJGoSelection.getObjectAtPos(localJGoListPosition);
                localJGoDocument.addObjectAtTail(localJGoObject);
            }
            updateActions();
            localJGoDocument.endTransaction(this.MoveToFrontAction.toString());
        }
    }

    void moveToBackAction() {
        if (!objectHelp("Editor_EditMenu_MoveToBack")) {
            GCView localGCView = myCurrentView;
            JGoDocument localJGoDocument = myCurrentView.getDocument();
            localJGoDocument.startTransaction();
            JGoSelection localJGoSelection = localGCView.getSelection();
            for (JGoListPosition localJGoListPosition = localJGoSelection.getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = localJGoSelection.getNextObjectPos(localJGoListPosition)) {
                JGoObject localJGoObject = localJGoSelection.getObjectAtPos(localJGoListPosition);
                localJGoDocument.addObjectAtHead(localJGoObject);
            }
            updateActions();
            localJGoDocument.endTransaction(this.MoveToBackAction.toString());
        }
    }

    public void zoomInAction() {
        double d = Math.rint(myCurrentView.getScale() / 0.8999999761581421D * 100.0D) / 100.0D;
        myCurrentView.setScale(d);
        updateActions();
    }

    public void zoomOutAction() {
        double d = Math.rint(myCurrentView.getScale() * 0.8999999761581421D * 100.0D) / 100.0D;
        myCurrentView.setScale(d);
        updateActions();
    }

    void zoomResetAction() {
        if (!objectHelp("Editor_ViewMenu_ZoomReset")) {
            double d = 1.0D;
            myCurrentView.setScale(d);
            updateActions();
        }
    }

    void zoomToFitAction() {
        if (!objectHelp("Editor_ViewMenu_ZoomToFit")) {
            double d1 = 1.0D;
            if (!myCurrentView.getDocument().isEmpty()) {
                double d2 = myCurrentView.getExtentSize().width;
                double d3 = myCurrentView.getPrintDocumentSize().width;
                double d4 = myCurrentView.getExtentSize().height;
                double d5 = myCurrentView.getPrintDocumentSize().height;
                d1 = Math.min(d2 / d3, d4 / d5);
            }
            if (d1 > 2.0D) {
                d1 = 1.0D;
            }
            d1 *= myCurrentView.getScale();
            myCurrentView.setScale(d1);
            myCurrentView.setViewPosition(0, 0);
            updateActions();
        }
    }

    void cutAction() {
        if (!objectHelp("Editor_EditMenu_Cut")) {
            GCView localGCView = myCurrentView;
            JGoSelection localJGoSelection = localGCView.getSelection();
            localGCView.deleteHierarchies(localJGoSelection);
            localGCView.getDocument().startTransaction();
            localGCView.cut();
            localGCView.pastePointSet = false;
            GCView.offsetLevel = 1;
            localGCView.getDocument().endTransaction("Cut");
            updateActions();
        }
    }

    void pasteAction() {
        if ((!objectHelp("Editor_EditMenu_Paste")) && (myCurrentView.getDocument().isModifiable())) {
            myCurrentView.getDocument().startTransaction();
            myCurrentView.paste();
            myCurrentView.getDocument().endTransaction("Paste");
            updateActions();
        }
    }

    void copyAction() {
        if (!objectHelp("Editor_EditMenu_Copy")) {
            myCurrentView.getDocument().startTransaction();
            myCurrentView.copy();
            myCurrentView.pastePointSet = false;
            GCView.offsetLevel = 1;
            myCurrentView.getDocument().endTransaction("Copy");
        }
    }

    void groupAction() {
        if (!objectHelp("Editor_EditMenu_Group")) {
            JGoDocument localJGoDocument = myCurrentView.getDocument();
            localJGoDocument.startTransaction();
            JGoSelection localJGoSelection = myCurrentView.getSelection();
            GCGroup localGCGroup = new GCGroup();
            localGCGroup.notifyModificationBegin();
            localGCGroup.setSelectable(true);
            localGCGroup.setDraggable(true);
            localJGoDocument.addObjectAtTail(localGCGroup);
            Vector localVector1 = new Vector();
            Vector localVector2 = new Vector();
            Object localObject2;
            Object localObject3;
            Object localObject4;
            for (JGoListPosition localJGoListPosition = localJGoSelection.getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = localJGoSelection.getNextObjectPos(localJGoListPosition)) {
                Object localObject1 = localJGoSelection.getObjectAtPos(localJGoListPosition);
                if ((localObject1 instanceof Connectable)) {
                    localObject2 = ((Connectable) localObject1).getAllLinks();
                    localObject3 = ((Vector) localObject2).elements();
                    while (((Enumeration) localObject3).hasMoreElements()) {
                        localObject4 = (GCLink) ((Enumeration) localObject3).nextElement();
                        if ((!localVector2.contains(localObject4)) && (!localJGoSelection.isInSelection((JGoObject) localObject4))) {
                            localVector2.add(localObject4);
                        }
                    }
                }
                if ((localObject1 instanceof GCGroup)) {
                    ((GCGroup) localObject1).updateChildren();
                }
                if (!localVector1.contains(localObject1)) {
                    localVector1.add(localObject1);
                }
            }
            Object localObject1 = localVector1.elements();
            while (((Enumeration) localObject1).hasMoreElements()) {
                localObject2 = ((Enumeration) localObject1).nextElement();
                if (((localObject2 instanceof JGoObject)) && (!(localObject2 instanceof GCLink)) && (!(localObject2 instanceof GCGroup))) {
                    localObject3 = (JGoObject) localObject2;
                    localJGoDocument.removeObject((JGoObject) localObject3);
                    localGCGroup.addObjectAtTail((JGoObject) localObject3);
                }
            }
            localObject1 = localVector1.elements();
            while (((Enumeration) localObject1).hasMoreElements()) {
                localObject2 = ((Enumeration) localObject1).nextElement();
                if ((localObject2 instanceof GCGroup)) {
                    ((GCGroup) localObject2).reGroup(localGCGroup);
                }
            }
            localObject1 = localVector1.elements();
            JGoPort localJGoPort;
            while (((Enumeration) localObject1).hasMoreElements()) {
                localObject2 = ((Enumeration) localObject1).nextElement();
                if ((localObject2 instanceof GCLink)) {
                    localObject3 = (GCLink) localObject2;
                    localJGoDocument.removeObject((JGoObject) localObject3);
                    localGCGroup.addObjectAtTail((JGoObject) localObject3);
                    localObject4 = ((GCLink) localObject3).getFromPort();
                    localJGoPort = ((GCLink) localObject3).getToPort();
                    ((GCLink) localObject3).setFromPort(null);
                    ((GCLink) localObject3).setToPort(null);
                    ((GCLink) localObject3).setFromPort((JGoPort) localObject4);
                    ((GCLink) localObject3).setToPort(localJGoPort);
                }
            }
            localObject1 = localVector2.elements();
            while (((Enumeration) localObject1).hasMoreElements()) {
                localObject2 = ((Enumeration) localObject1).nextElement();
                if ((localObject2 instanceof GCLink)) {
                    localObject3 = (GCLink) localObject2;
                    localJGoDocument.addObjectAtTail((JGoObject) localObject3);
                    localObject4 = ((GCLink) localObject3).getFromPort();
                    localJGoPort = ((GCLink) localObject3).getToPort();
                    ((GCLink) localObject3).setFromPort(null);
                    ((GCLink) localObject3).setToPort(null);
                    ((GCLink) localObject3).setFromPort((JGoPort) localObject4);
                    ((GCLink) localObject3).setToPort(localJGoPort);
                }
            }
            localGCGroup.notifyModificationEnd();
            localJGoSelection.extendSelection(localGCGroup);
            localJGoDocument.endTransaction(this.GroupAction.toString());
        }
    }

    void ungroupAction() {
        if (!objectHelp("Editor_EditMenu_Ungroup")) {
            myCurrentView.getDocument().startTransaction();
            JGoSelection localJGoSelection = myCurrentView.getSelection();
            JGoObject localJGoObject = localJGoSelection.getPrimarySelection();
            if ((localJGoObject instanceof GCGroup)) {
                GCGroup localGCGroup = (GCGroup) localJGoObject;
                localGCGroup.notifyModificationBegin();
                GCDocument localGCDocument = localGCGroup.getDocument();
                Vector localVector1 = new Vector();
                Vector localVector2 = new Vector();
                JGoListPosition localJGoListPosition = localGCGroup.getFirstObjectPos();
                Object localObject2;
                Object localObject3;
                Object localObject4;
                if (localJGoListPosition != null) {
                    while (localJGoListPosition != null) {
                        Object localObject1 = localGCGroup.getObjectAtPos(localJGoListPosition);
                        if ((localObject1 instanceof Connectable)) {
                            localObject2 = ((Connectable) localObject1).getAllLinks();
                            localObject3 = ((Vector) localObject2).elements();
                            while (((Enumeration) localObject3).hasMoreElements()) {
                                localObject4 = (GCLink) ((Enumeration) localObject3).nextElement();
                                if ((!localVector2.contains(localObject4)) && (!localJGoSelection.isInSelection((JGoObject) localObject4))) {
                                    localVector2.add(localObject4);
                                }
                            }
                        }
                        if (!localVector1.contains(localObject1)) {
                            localVector1.add(localObject1);
                        }
                        if ((localObject1 instanceof GCGroup)) {
                            ((GCGroup) localObject1).updateChildren();
                        }
                        ((JGoObject) localObject1).setSelectable(true);
                        localJGoListPosition = localGCGroup.getNextObjectPos(localJGoListPosition);
                    }
                }
                Object localObject1 = localVector1.elements();
                while (((Enumeration) localObject1).hasMoreElements()) {
                    localObject2 = ((Enumeration) localObject1).nextElement();
                    if (((localObject2 instanceof JGoObject)) && (!(localObject2 instanceof GCLink)) && (!(localObject2 instanceof GCGroup))) {
                        localObject3 = (JGoObject) localObject2;
                        localGCGroup.removeObject((JGoObject) localObject3);
                        localGCDocument.addObjectAtTail((JGoObject) localObject3);
                    }
                }
                localObject1 = localVector1.elements();
                while (((Enumeration) localObject1).hasMoreElements()) {
                    localObject2 = ((Enumeration) localObject1).nextElement();
                    if ((localObject2 instanceof GCGroup)) {
                        ((GCGroup) localObject2).reGroup(null);
                    }
                }
                localObject1 = localVector1.elements();
                JGoPort localJGoPort;
                while (((Enumeration) localObject1).hasMoreElements()) {
                    localObject2 = ((Enumeration) localObject1).nextElement();
                    if ((localObject2 instanceof GCLink)) {
                        localObject3 = (GCLink) localObject2;
                        localGCGroup.removeObject((JGoObject) localObject3);
                        localGCDocument.addObjectAtTail((JGoObject) localObject3);
                        localObject4 = ((GCLink) localObject3).getFromPort();
                        localJGoPort = ((GCLink) localObject3).getToPort();
                        ((GCLink) localObject3).setFromPort(null);
                        ((GCLink) localObject3).setToPort(null);
                        ((GCLink) localObject3).setFromPort((JGoPort) localObject4);
                        ((GCLink) localObject3).setToPort(localJGoPort);
                    }
                }
                localObject1 = localVector2.elements();
                while (((Enumeration) localObject1).hasMoreElements()) {
                    localObject2 = ((Enumeration) localObject1).nextElement();
                    if ((localObject2 instanceof GCLink)) {
                        localObject3 = (GCLink) localObject2;
                        localGCDocument.addObjectAtTail((JGoObject) localObject3);
                        localObject4 = ((GCLink) localObject3).getFromPort();
                        localJGoPort = ((GCLink) localObject3).getToPort();
                        ((GCLink) localObject3).setFromPort(null);
                        ((GCLink) localObject3).setToPort(null);
                        ((GCLink) localObject3).setFromPort((JGoPort) localObject4);
                        ((GCLink) localObject3).setToPort(localJGoPort);
                    }
                }
                localGCDocument.removeObject(localGCGroup);
            }
            myCurrentView.getDocument().endTransaction(this.UngroupAction.toString());
        }
    }

    void toggleObjectifyAction() {
        myCurrentView.getDocument().startTransaction();
        ((GCGroup) myCurrentView.getSelection().getPrimarySelection()).toggleObjectify();
        updateActions();
        myCurrentView.getDocument().endTransaction(this.ToggleObjectifyAction.toString());
    }

    void closeWorkspaceAction() {
        myCurrentView.getInternalFrame().doDefaultCloseAction();        
    }

    /** 
     * Method liée à l'action new
     * @return 
     */
    GCDocument newAction() {
        if (!objectHelp("Editor_FileMenu_New")) {
            GCDocument localGCDocument = new GCDocument();
            localGCDocument.setSuspendUpdates(true);
            String str = "J" + Integer.toString(this.myDocCount++);
            localGCDocument.setName(str);
            topGrafcharts.add(localGCDocument);
            final GCView localGCView = new GCView(localGCDocument);
            localGCDocument.setView(localGCView);
            MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame("TL: " + localGCDocument.getName(), false);
            localMyJInternalFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            localGCDocument.setPaperColor(new Color(255, 255, 255));
            localGCView.initialize(this, localMyJInternalFrame);
            localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = localGCView;
                    Editor.topLevelView = true;
                    Editor.compilationView = true;
                    Editor.myCurrentView.requestFocus();
                    AppAction.updateAllActions();
                }

                public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.this.closeMacros(localGCView.getDoc());
                    Editor.topGrafcharts.remove(localGCView.getDoc());
                    Editor.myCurrentView = null;
                    Editor.topLevelView = false;
                    Editor.compilationView = false;
                    AppAction.updateAllActions();
                }

                public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }
            });
            localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
            localMyJInternalFrame.getContentPane().add(localGCView);
            localMyJInternalFrame.setSize(400, 600);
            getDesktop().add(localMyJInternalFrame);
            localMyJInternalFrame.show();
            localGCView.initializeDragDropHandling();
            localGCDocument.setSuspendUpdates(false);
            return localGCDocument;
        }
        return null;
    }

    public void closeMacros(GCDocument paramGCDocument) {
        if (paramGCDocument != null) {
            JGoListPosition localJGoListPosition = paramGCDocument.getFirstObjectPos();
            for (JGoObject localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition)) {
                Object localObject;
                if ((localJGoObject instanceof MacroStep)) {
                    localObject = (MacroStep) localJGoObject;
                    closeMacros(((MacroStep) localObject).myContentDocument);
                    if (((MacroStep) localObject).frame != null) {
                        try {
                            ((MacroStep) localObject).frame.setClosed(true);
                        } catch (Exception localException1) {
                        }
                    }
                }
                if ((localJGoObject instanceof WorkspaceObject)) {
                    localObject = (WorkspaceObject) localJGoObject;
                    closeMacros(((WorkspaceObject) localObject).myContentDocument);
                    if (((WorkspaceObject) localObject).frame != null) {
                        try {
                            ((WorkspaceObject) localObject).frame.setClosed(true);
                        } catch (Exception localException2) {
                        }
                    }
                }
                if ((localJGoObject instanceof XMLMessageIn)) {
                    localObject = (XMLMessageIn) localJGoObject;
                    closeMacros(((XMLMessageIn) localObject).myContentDocument);
                    if (((XMLMessageIn) localObject).frame != null) {
                        closeMacros(((XMLMessageIn) localObject).myContentDocument);
                        try {
                            ((XMLMessageIn) localObject).frame.setClosed(true);
                        } catch (Exception localException3) {
                        }
                    }
                }
                if ((localJGoObject instanceof XMLMessageOut)) {
                    localObject = (XMLMessageOut) localJGoObject;
                    closeMacros(((XMLMessageOut) localObject).myContentDocument);
                    if (((XMLMessageOut) localObject).frame != null) {
                        try {
                            ((XMLMessageOut) localObject).frame.setClosed(true);
                        } catch (Exception localException4) {
                        }
                    }
                }
                if ((localJGoObject instanceof GrafcetProcedure)) {
                    localObject = (GrafcetProcedure) localJGoObject;
                    closeMacros(((GrafcetProcedure) localObject).myContentDocument);
                    if (((GrafcetProcedure) localObject).frame != null) {
                        try {
                            ((GrafcetProcedure) localObject).frame.setClosed(true);
                        } catch (Exception localException5) {
                        }
                    }
                }
                if ((localJGoObject instanceof SFCPlotter)) {
                    localObject = (SFCPlotter) localJGoObject;
                    if (((SFCPlotter) localObject).frame != null) {
                        try {
                            ((SFCPlotter) localObject).frame.setClosed(true);
                        } catch (Exception localException6) {
                        }
                    }
                }
                localJGoListPosition = paramGCDocument.getNextObjectPos(localJGoListPosition);
            }
        }
    }

    void openAllAction() {
        if (!objectHelp("Editor_FileMenu_OpenAll")) {
            GCDocument localGCDocument = null;
            MyJFileChooser localMyJFileChooser;
            if (myCurrentView != null) {
                localMyJFileChooser = new MyJFileChooser(myCurrentView.getDoc().getReadFileLocation());
            } else {
                localMyJFileChooser = new MyJFileChooser("");
            }
            int i = localMyJFileChooser.showOpenDialog(null);
            if (i == 0) {
                String str = localMyJFileChooser.getSelectedFile().getAbsolutePath();
                Document localDocument = loadXMLDocumentFromFile(str);
                if (localDocument != null) {
                    Element localElement1 = localDocument.getDocumentElement();
                    if (localElement1.getTagName().equals(GCDocument.documentCollectionTag)) {
                        NodeList localNodeList = localElement1.getChildNodes();
                        for (int j = 0; j < localNodeList.getLength(); j++) {
                            Node localNode = localNodeList.item(j);
                            if (localNode.getNodeType() == 1) {
                                Element localElement2 = (Element) localNode;
                                if (localElement2.getTagName().equals(GCDocument.documentTag)) {
                                    localGCDocument = new GCDocument();
                                    localGCDocument.setSuspendUpdates(true);
                                    localGCDocument.loadXML(localElement2);
                                    topGrafcharts.add(localGCDocument);
                                    final GCView localGCView = new GCView(localGCDocument);
                                    localGCDocument.setView(localGCView);
                                    MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localGCDocument.getName(), false);
                                    localMyJInternalFrame.setDefaultCloseOperation(2);
                                    localGCView.initialize(this, localMyJInternalFrame);
                                    localGCView.setScale(localGCDocument.currentScale);
                                    if (localGCDocument.point != null) {
                                        localGCView.setViewPosition(localGCDocument.point);
                                    }
                                    localMyJInternalFrame.setBounds(localGCDocument.bounds);
                                    localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                                        public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                                            Editor.myCurrentView = localGCView;
                                            Editor.compilationView = true;
                                            Editor.topLevelView = true;
                                            localGCView.requestFocus();
                                            AppAction.updateAllActions();
                                        }

                                        public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                                        }

                                        public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                                        }

                                        public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                                            Editor.topGrafcharts.remove(localGCView.getDoc());
                                            Editor.compilationView = false;
                                            Editor.myCurrentView = null;
                                            AppAction.updateAllActions();
                                        }

                                        public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                                        }

                                        public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                                        }

                                        public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                                        }
                                    });
                                    localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
                                    localMyJInternalFrame.getContentPane().add(localGCView);
                                    getDesktop().add(localMyJInternalFrame);
                                    localMyJInternalFrame.show();
                                    localGCView.initializeDragDropHandling();
                                    localGCDocument.setSuspendUpdates(false);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "The file is a single workspace and must thus be opened with \"Open\".", "Open Document Error", 0);
                    }
                }
                this.writeAllFileLocation = str;
            }
        }
    }

    void openAction() {
        if (!objectHelp("Editor_FileMenu_Open")) {
            
            MyJFileChooser localMyJFileChooser;
            if (myCurrentView != null) {
                localMyJFileChooser = new MyJFileChooser(myCurrentView.getDoc().getReadFileLocation());
            } else {
                localMyJFileChooser = new MyJFileChooser("");
            }
            int i = localMyJFileChooser.showOpenDialog(null);
            if (i == 0) {
                String path = localMyJFileChooser.getSelectedFile().getAbsolutePath();
                openRealFileAction(path);       
            }
        }
    }

    public static GCDocument loadObjects(InputStream paramInputStream)
            throws IOException, ClassNotFoundException {
        ObjectInputStream localObjectInputStream = new ObjectInputStream(paramInputStream);
        Object localObject = localObjectInputStream.readObject();
        if ((localObject instanceof GCDocument)) {
            GCDocument localGCDocument = (GCDocument) localObject;
            return localGCDocument;
        }
        return null;
    }

    void saveAction() {
        if (!objectHelp("Editor_FileMenu_Save")) {
            GCDocument localGCDocument;
            for (localGCDocument = (GCDocument) myCurrentView.getDocument(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
            }
            removeUniqueNames(localGCDocument);
            if (localGCDocument.getWriteFileLocation().equals("")) { //Si le fichier n'existe pas encore
                saveAsAction();
            } else { //Sinon
                store(localGCDocument);
            }
        }
    }

    void saveAllAction() {
        if (!objectHelp("Editor_FileMenu_SaveAll")) {
            if (this.writeAllFileLocation.equals("")) {
                saveAllAsAction();
            } else {
                storeAll();
            }
        }
    }

    void saveAllAsAction() {
        if (!objectHelp("Editor_FileMenu_SaveAllAs")) {
            MyJFileChooser localMyJFileChooser = new MyJFileChooser(this.writeAllFileLocation);
            int i = localMyJFileChooser.showSaveDialog(null);
            if (i == 0) {
                String str = localMyJFileChooser.getSelectedFile().getAbsolutePath();
                this.writeAllFileLocation = str;
                try {
                    Document localDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                    Element localElement = localDocument.createElement(GCDocument.documentCollectionTag);
                    localDocument.appendChild(localElement);
                    Iterator localIterator = topGrafcharts.getStorage().iterator();
                    while (localIterator.hasNext()) {
                        GCDocument localGCDocument = (GCDocument) localIterator.next();
                        localGCDocument.currentScale = localGCDocument.getView().getScale();
                        localGCDocument.point = localGCDocument.getView().getViewPosition();
                        localGCDocument.bounds = localGCDocument.getView().myInternalFrame.getBounds();
                        localGCDocument.storeXML(localDocument);
                    }
                    saveXMLDocumentToFile(localDocument, this.writeAllFileLocation);
                } catch (Exception localException) {
                    Utils.writeException(localException);
                    JOptionPane.showMessageDialog(this, "An internal error caused the save to fail.", "Save Failed", 0);
                }
            }
        }
    }

    void saveAsAction() {
        if (!objectHelp("Editor_FileMenu_SaveAs")) {
            GCDocument localGCDocument;
            for (localGCDocument = myCurrentView.getDoc(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
            }
            removeUniqueNames(localGCDocument);
            MyJFileChooser localMyJFileChooser = new MyJFileChooser(localGCDocument.getWriteFileLocation());
            if (localMyJFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String selectedPath = localMyJFileChooser.getSelectedFile().getAbsolutePath();
                localGCDocument.setReadFileLocation(selectedPath);
                localGCDocument.setWriteFileLocation(selectedPath);
                localGCDocument.setName(localMyJFileChooser.getSelectedFile().getName());
                localGCDocument.rgbColor = localGCDocument.getPaperColor().getRGB();
                localGCDocument.currentScale = localGCDocument.getView().getScale();
                localGCDocument.point = localGCDocument.getView().getViewPosition();
                localGCDocument.bounds = localGCDocument.getView().myInternalFrame.getBounds();                
                try {
                    Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                    localGCDocument.storeXML(xmlDoc);
                    saveXMLDocumentToFile(xmlDoc, selectedPath);
                } catch (Exception localException) {
                    Utils.writeException(localException);
                    JOptionPane.showMessageDialog(this, "An internal error caused the save to fail.", "Save Failed", 0);
                }
                localGCDocument.getView().updateTitle();
            }
        }
    }

    public static Document loadXMLDocumentFromFile(String paramString) {
        Object localObject1 = null;
        FileInputStream localFileInputStream = null;
        try {
            localFileInputStream = new FileInputStream(new File(paramString).getCanonicalFile());
            DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
            Document localDocument = localDocumentBuilder.parse(localFileInputStream);
            if (Utils.getSaveVersion(localDocument.getDocumentElement()) <= 8) {
                localObject1 = localDocument;
            } else {
                JOptionPane.showMessageDialog(null, "The file is saved by a newer and incompatible version of JGrafchart.", "Open Document Error", 0);
            }
            return (Document) localObject1;
        } catch (IOException localIOException) {
            JOptionPane.showMessageDialog(null, localIOException, "Open Document IOException", 0);
        } catch (Exception localException3) {
            Utils.writeException(localException3);
            JOptionPane.showMessageDialog(null, localException3, "Open Document Exception", 0);
        } finally {
            try {
                if (localFileInputStream != null) {
                    localFileInputStream.close();
                }
            } catch (Exception localException5) {
            }
        }
        return null;
    }

    /**
     * Persiste le doc XML sur le disque dans le chemin spécifié
     * @param xmlDoc
     * @param path 
     */
    public static void saveXMLDocumentToFile(Document xmlDoc, String path) {
        xmlDoc.setXmlStandalone(true);
        ((Element) xmlDoc.getFirstChild()).setAttribute("saveVersion", Integer.toString(8));
        File pathFile = null;
        File tempFile = null;
        try {
            pathFile = new File(path).getCanonicalFile();
            File dirFile = new File(pathFile.getParent());
            tempFile = File.createTempFile("JGTempSave", ".xml", dirFile);
        } catch (IOException localIOException) {
            Utils.writeException(localIOException);
            JOptionPane.showMessageDialog(null, localIOException, "Save Document Error: Temp file open", 0);
            return;
        }
        FileOutputStream localFileOutputStream = null;
        int i = 0;
        try {
            localFileOutputStream = new FileOutputStream(tempFile);
            try {
                Transformer localTransformer = TransformerFactory.newInstance().newTransformer();
                localTransformer.setOutputProperty("method", "xml");
                localTransformer.setOutputProperty("version", "1.0");
                localTransformer.setOutputProperty("encoding", "UTF-8");
                localTransformer.setOutputProperty("omit-xml-declaration", "no");
                localTransformer.setOutputProperty("standalone", "yes");
                localTransformer.setOutputProperty("indent", "yes");
                localTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                DOMSource localDOMSource = new DOMSource(xmlDoc);
                localTransformer.transform(localDOMSource, new StreamResult(localFileOutputStream));
                i = 1;
            } catch (IllegalArgumentException | TransformerException localException1) {
                Utils.writeException(localException1);
                JOptionPane.showMessageDialog(null, localException1, "Save Document Error: Temp file write", 0);
            }
            try {
                if (localFileOutputStream != null) {
                    localFileOutputStream.close();
                }
            } catch (IOException localException2) {
            }
            if (i == 0) {
                System.out.println("grafchart.sfc.Editor.saveXMLDocumentToFile() - 3022");
                tempFile.delete();
            }
        } catch (HeadlessException | FileNotFoundException localException3) {
            Utils.writeException(localException3);
            JOptionPane.showMessageDialog(null, localException3, "Save Document Error", 0);
        } finally {
            try {
                if (localFileOutputStream != null) {
                    localFileOutputStream.close();
                }
            } catch (Exception localException5) {
            }
        }
        if (pathFile.exists()) {
            pathFile.delete();
        }
        tempFile.renameTo(pathFile);
    }

    void storeAll() {
        if (!this.writeAllFileLocation.equals("")) {
            try {
                Document localDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                Element localElement = localDocument.createElement(GCDocument.documentCollectionTag);
                localDocument.appendChild(localElement);
                Iterator localIterator = topGrafcharts.getStorage().iterator();
                while (localIterator.hasNext()) {
                    GCDocument localGCDocument = (GCDocument) localIterator.next();
                    localGCDocument.rgbColor = localGCDocument.getPaperColor().getRGB();
                    localGCDocument.currentScale = localGCDocument.getView().getScale();
                    localGCDocument.point = localGCDocument.getView().getViewPosition();
                    localGCDocument.bounds = localGCDocument.getView().myInternalFrame.getBounds();
                    localGCDocument.storeXML(localDocument);
                }
                saveXMLDocumentToFile(localDocument, this.writeAllFileLocation);
            } catch (Exception localException) {
                Utils.writeException(localException);
                JOptionPane.showMessageDialog(this, "An internal error caused the save to fail.", "Save Failed", 0);
            }
        }
    }

    void store(GCDocument paramGCDocument) {
        if (!paramGCDocument.getWriteFileLocation().equals("")) {
            paramGCDocument.rgbColor = paramGCDocument.getPaperColor().getRGB();
            paramGCDocument.currentScale = paramGCDocument.getView().getScale();
            paramGCDocument.bounds = paramGCDocument.getView().myInternalFrame.getBounds();
            paramGCDocument.point = paramGCDocument.getView().getViewPosition();
            try {
                Document localDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                paramGCDocument.storeXML(localDocument);
                saveXMLDocumentToFile(localDocument, paramGCDocument.getWriteFileLocation());
            } catch (ParserConfigurationException localException) {
                Utils.writeException(localException);
                JOptionPane.showMessageDialog(this, "An internal error caused the save to fail.", "Save Failed", 0);
            }
        }
    }

    public void propertiesAction() {
        if (!objectHelp("Editor_EditMenu_Properties")) {
            GCView localGCView = myCurrentView;
            if (localGCView != null) {
                new GrafcetDialog(localGCView.getFrame(), localGCView.getDoc(), localGCView).setVisible(true);
            }
        }
    }

    void gridAction() {
        if (!objectHelp("Editor_EditMenu_Grid")) {
            try {
                GridOptionsDialog localGridOptionsDialog = new GridOptionsDialog(this, "Workspace Properties", myCurrentView);
                localGridOptionsDialog.setVisible(true);
            } catch (Exception localException) {
            }
        }
    }

    public void treeViewAction() {
        if (!objectHelp("Editor_ViewMenu_TreeView")) {
            this.treeDisplay = new TreeDisplay(this, myCurrentView.getDoc());
            AppAction.updateAllActions();
        }
    }

    public static void hidePaletteAction() {
        if (!objectHelp("Editor_ViewMenu_HidePalette")) {
            if(paletteHidden){
                paletteHidden = false;
                contentPane.add(tabbedPane, "West");
            } else {
                paletteHidden = true;
                contentPane.remove(tabbedPane);                
            }    
            contentPane.repaint();
            contentPane.validate();
            AppAction.updateAllActions();
        }
    }

    public static void showPaletteAction() {
        if ((!objectHelp("Editor_ViewMenu_ShowPalette")) && (paletteHidden)) {
            paletteHidden = false;
            contentPane.add(tabbedPane, "West");
            contentPane.validate();
            AppAction.updateAllActions();
        }
    }

    void hideToolbarAction() {
        if (!objectHelp("Editor_ViewMenu_HideToolbar")) {
            if(this.toolbarHidden){
                this.toolbarHidden = false;
                contentPane.add(this.toolBar, "North");                
            } else {
                this.toolbarHidden = true;
                contentPane.remove(this.toolBar);
            }            
            contentPane.repaint();
            contentPane.validate();
            AppAction.updateAllActions();
        }
    }

    void showToolbarAction() {
        if (!objectHelp("Editor_ViewMenu_ShowToolbar")) {
            this.toolbarHidden = false;
            
            contentPane.validate();
            AppAction.updateAllActions();
        }
    }

    void executeAction() {
        if (!objectHelp("Editor_ExecuteMenu_Execute")) {
            GCDocument localGCDocument;
            for (localGCDocument = (GCDocument) myCurrentView.getDocument(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
                if (((localGCDocument.owner instanceof MacroStep)) && (!(localGCDocument.owner instanceof ProcedureStep))) {
                    ((MacroStep) localGCDocument.owner).parentView.setDragDropEnabled(false);
                }
                if ((localGCDocument.owner instanceof WorkspaceObject)) {
                    ((WorkspaceObject) localGCDocument.owner).parentView.setDragDropEnabled(false);
                }
                if ((localGCDocument.owner instanceof XMLMessageIn)) {
                    ((XMLMessageIn) localGCDocument.owner).parentView.setDragDropEnabled(false);
                }
                if ((localGCDocument.owner instanceof XMLMessageOut)) {
                    ((XMLMessageOut) localGCDocument.owner).parentView.setDragDropEnabled(false);
                }
                if ((localGCDocument.owner instanceof GrafcetProcedure)) {
                    ((GrafcetProcedure) localGCDocument.owner).parentView.setDragDropEnabled(false);
                }
            }
            startDocument(localGCDocument);            
        }
    }
    
    private void compileDocument(GCDocument gcDoc) {
        generateWarnings = true;
        warningAboutNameConflicts = true;
        SymbolTableHelper.warnAboutNameConflicts(gcDoc);
        warningAboutNameConflicts = false;
        addUniqueNames(gcDoc, 0);
        setOutHack(gcDoc, gcDoc);
        compileDocument(gcDoc, false);
        generateWarnings = false;
        //compileDocument(gcDoc, false);
        if (gcDoc.compiledOK) {
            removeUniqueNames(gcDoc);
        }
    }

    private void startDocument(GCDocument paramGCDocument) {
        paramGCDocument.initializeDocument(paramGCDocument, paramGCDocument.isSimulating());
        paramGCDocument.sortInputs();
        paramGCDocument.getView().setDragDropEnabled(false);
        paramGCDocument.setDragAndDrop(false);
        paramGCDocument.start();
    }

    private void stopDocument(GCDocument paramGCDocument) {
        paramGCDocument.stopThread();
        paramGCDocument.stopDocument();
        paramGCDocument.getView().setDragDropEnabled(true);
        paramGCDocument.setDragAndDrop(true);
        paramGCDocument.setSkipsUndoManager(false);
    }

    private void pauseDocument(GCDocument paramGCDocument) {
        paramGCDocument.pauseThread();
    }

    private void resumeDocument(GCDocument paramGCDocument) {
        paramGCDocument.resumeThread();
    }

    private void stepDocument(GCDocument paramGCDocument) {
        paramGCDocument.stepThread();
    }

    /**
     * Compilation des objets de type group d'objet comme les macro
     * @param jGoObject objet à comiler
     * @param jGoObjetList s
     */
    void compileJGoObject1(JGoObject jGoObject, ArrayList jGoObjetList) {
        if ((jGoObject instanceof LabCommObject)) {
            ((LabCommObject) jGoObject).compile();
        }
        Object localObject;
        if (((jGoObject instanceof MacroStep)) && (!(jGoObject instanceof ProcedureStep))) {
            localObject = (MacroStep) jGoObject;
            compileDocument1(((MacroStep) localObject).myContentDocument);
        }
        if ((jGoObject instanceof GrafcetProcedure)) {
            localObject = (GrafcetProcedure) jGoObject;
            compileDocument1(((GrafcetProcedure) localObject).myContentDocument);
        }
        if (((jGoObject instanceof WorkspaceObject)) && (!(jGoObject instanceof StepFusionSet))) {
            localObject = (WorkspaceObject) jGoObject;
            compileDocument1(((WorkspaceObject) localObject).myContentDocument);
        }
        if ((jGoObject instanceof GCGroup)) {
            localObject = (GCGroup) jGoObject;
            jGoObjetList.addAll(((GCGroup) localObject).getSymbolTable());
            compileDocument1((GCCollection) localObject);
        }
        if ((jGoObject instanceof XMLMessageIn)) {
            localObject = (XMLMessageIn) jGoObject;
            compileDocument1(((XMLMessageIn) localObject).myContentDocument);
        }
        if ((jGoObject instanceof XMLMessageOut)) {
            localObject = (XMLMessageOut) jGoObject;
            compileDocument1(((XMLMessageOut) localObject).myContentDocument);
        }
    }

    /**
     * Compilation des éléments de type unitaire comme les steps les transitions
     * @param jGoObject objet à compiler
     * @param symbolTable
     * @param paramBoolean
     * @param jGoObjetList 
     */
    void compileJGoObject2(JGoObject jGoObject, SymbolTableObject symbolTable, boolean paramBoolean, ArrayList jGoObjetList) {
        Object jGoObj;
        String str1;
        if ((jGoObject instanceof GenericTransition)) {
            jGoObj = (GenericTransition) jGoObject;
            ((GenericTransition) jGoObj).compileStructure();
            str1 = ((GenericTransition) jGoObj).getLabelText();
            ((GenericTransition) jGoObj).setTextColor(black);
            ((GenericTransition) jGoObj).node = null;
            /*
            TransitionParser tParser = new TransitionParser(new StringReader(str1));
            try {
                ((GenericTransition) jGoObj).node = ((TransitionParser) tParser).Start();
            } catch (ParseException localParseException) {
                ((GenericTransition) jGoObj).setTextColor(red);
                String str2 = "Parsing problem with transition in " + ((GenericTransition) jGoObj).getDocument().getFullName() + ": " + localParseException.getMessage();
                giveWarning(str2);
            } catch (Throwable localThrowable1) {
                ((GenericTransition) jGoObj).setTextColor(red);
                Utils.writeException(localThrowable1);
                giveWarning("Unhandled exception during transition parsing for " + ((GenericTransition) jGoObj).getDocument().getFullName() + ".");
            }
            if (((GenericTransition) jGoObj).node != null) {
                try {
                    boolean bool = ((GenericTransition) jGoObj).node.compile(symbolTable);
                    if (!bool) {
                        ((GenericTransition) jGoObj).setTextColor(red);
                    } else {
                        ((GenericTransition) jGoObj).setTextColor(black);
                    }
                    if (!((GenericTransition) jGoObj).node.messages().isEmpty()) {
                        giveInformation("Compilation messages for transition in \"" + ((GenericTransition) jGoObj).getDocument().getFullName() + "\".");
                        giveWarnings(((GenericTransition) jGoObj).node.messages());
                    }
                } catch (Throwable localThrowable2) {
                    Utils.writeException(localThrowable2);
                    giveWarning("Unhandled exception during transition compilation for " + ((GenericTransition) jGoObj).getDocument().getFullName() + ".");
                    ((GenericTransition) jGoObj).setTextColor(red);
                }
            }
            */
        }
        if ((jGoObject instanceof GCStep)) {
            jGoObj = (GCStep) jGoObject;
            str1 = ((GCStep) jGoObj).getActionText();
            JGoText jGoText = ((GCStep) jGoObj).myActions;
            //((GCStep) jGoObj).node = ActionCompiler.compileStatement(str1, symbolTable, "actions in step \"" + ((GCStep) jGoObj).getFullName() + "\"", jGoText);
        }
        if (((jGoObject instanceof MacroStep)) && (!(jGoObject instanceof ProcedureStep))) {
            jGoObj = (MacroStep) jGoObject;
            compileDocument2(((MacroStep) jGoObj).myContentDocument, paramBoolean);
            str1 = ((MacroStep) jGoObj).actionText;
            //((MacroStep) jGoObj).node = ActionCompiler.compileStatement(str1, symbolTable, "actions in macro step \"" + ((MacroStep) jGoObj).getFullName() + "\"", null);
        }
        if ((jGoObject instanceof AssignmentButton)) {
            jGoObj = (AssignmentButton) jGoObject;
            str1 = ((AssignmentButton) jGoObj).actionString;
            ((AssignmentButton) jGoObj).node = ActionCompiler.compileStatement(str1, symbolTable, "ActionButton \"" + ((AssignmentButton) jGoObj).getFullName() + "\"", null);
        }
        if ((jGoObject instanceof GraphicalButton)) {
            jGoObj = (GraphicalButton) jGoObject;
            str1 = ((GraphicalButton) jGoObj).actionString;
            ((GraphicalButton) jGoObj).node = ActionCompiler.compileStatement(str1, symbolTable, "GraphicalButton \"" + ((GraphicalButton) jGoObj).getFullName() + "\"", null);
        }
        if ((jGoObject instanceof InternalVariable)) {
            jGoObj = (InternalVariable) jGoObject;
            if ((((InternalVariable) jGoObj).isUpdated) && (((InternalVariable) jGoObj).expression.length() > 0)) {
                str1 = "Internal Variable \"" + ((InternalVariable) jGoObj).getFullName() + "\"";
                ((InternalVariable) jGoObj).node = ((SingleExpression) ActionCompiler.compile(((InternalVariable) jGoObj).expression, symbolTable, ActionCompiler.CompileType.SingleExpression, str1, null));
                if ((((InternalVariable) jGoObj).node != null) && (((InternalVariable) jGoObj).node.getExpr().hasReferences())) {
                    giveLightWarning(str1 + " will not be sorted since it contains references.");
                }
            }
        }
        if ((jGoObject instanceof SFCPlotter)) {
            jGoObj = (SFCPlotter) jGoObject;
            str1 = "Plotter \"" + ((SFCPlotter) jGoObj).getFullName() + "\"";
            if ((((SFCPlotter) jGoObj).chan1String.length() > 0) || (((SFCPlotter) jGoObj).chan2String.length() > 0)) {
                int i = 0;
                if (((SFCPlotter) jGoObj).chan1String.length() > 0) {
                    ((SFCPlotter) jGoObj).chan1Node = ActionCompiler.compile(((SFCPlotter) jGoObj).chan1String, symbolTable, ActionCompiler.CompileType.SingleExpression, str1, null);
                    if (((SFCPlotter) jGoObj).chan1Node != null) {
                        i++;
                    }
                }
                if (((SFCPlotter) jGoObj).chan2String.length() > 0) {
                    ((SFCPlotter) jGoObj).chan2Node = ActionCompiler.compile(((SFCPlotter) jGoObj).chan2String, symbolTable, ActionCompiler.CompileType.SingleExpression, str1, null);
                    if (((SFCPlotter) jGoObj).chan2Node != null) {
                        i++;
                    }
                }
                if (i > 0) {
                    ((SFCPlotter) jGoObj).initialize(i);
                }
            } else {
                giveWarning(str1 + " must specify at least one channel.");
            }
        }
        if ((jGoObject instanceof StepFusionSet)) {
            jGoObj = (StepFusionSet) jGoObject;
            if (!paramBoolean) {
                ((StepFusionSet) jGoObj).compile();
            }
        }
        if ((jGoObject instanceof GrafcetProcedure)) {
            jGoObj = (GrafcetProcedure) jGoObject;
            ((GrafcetProcedure) jGoObj).symbolTableObject = symbolTable;
            compileDocument2(((GrafcetProcedure) jGoObj).myContentDocument, true);
            ((GrafcetProcedure) jGoObj).paramNode = ((ProcParam) ActionCompiler.compile(((GrafcetProcedure) jGoObj).parameters, ((GrafcetProcedure) jGoObj).myContentDocument, ActionCompiler.CompileType.ProcParam, "procedure parameters in \"" + ((GrafcetProcedure) jGoObj).getFullName() + "\"", null));
        }
        if (((jGoObject instanceof WorkspaceObject)) && (!(jGoObject instanceof StepFusionSet))) {
            jGoObj = (WorkspaceObject) jGoObject;
            compileDocument2(((WorkspaceObject) jGoObj).myContentDocument, paramBoolean);
        }
        if ((jGoObject instanceof GCGroup)) {
            jGoObj = (GCGroup) jGoObject;
            jGoObjetList.addAll(((GCGroup) jGoObj).getSymbolTable());
            compileDocument2((GCCollection) jGoObj, paramBoolean);
        }
        GrafcetProcedure localGrafcetProcedure;
        GCDocument localGCDocument;
        if ((jGoObject instanceof XMLMessageIn)) {
            jGoObj = (XMLMessageIn) jGoObject;
            compileDocument2(((XMLMessageIn) jGoObj).myContentDocument, paramBoolean);
            ((XMLMessageIn) jGoObj).viewOwner = myCurrentView;
            if (((XMLMessageIn) jGoObj).useProcedure) {
                str1 = "procedure name in \"" + ((XMLMessageIn) jGoObj).getFullName() + "\"";
                ((XMLMessageIn) jGoObj).procNode = ((ProcCall) ActionCompiler.compile(((XMLMessageIn) jGoObj).gp, symbolTable, ActionCompiler.CompileType.ProcCall, str1, null));
                if (((XMLMessageIn) jGoObj).procNode != null) {
                    localGrafcetProcedure = Utils.referencableToGrafcetProcedure(((XMLMessageIn) jGoObj).procNode.getExpr().staticDecl(), false);
                    if (localGrafcetProcedure != null) {
                        ((XMLMessageIn) jGoObj).isDynamicProcedureCall = false;
                        ((XMLMessageIn) jGoObj).procedure = localGrafcetProcedure;
                        localGCDocument = new GCDocument();
                        localGCDocument.copyFromCollection(localGrafcetProcedure.myContentDocument);
                        localGCDocument.owner = ((GrafcetObject) jGoObj);
                        ((XMLMessageIn) jGoObj).paramNode = ((ProcParam) ActionCompiler.compile(((XMLMessageIn) jGoObj).parameters, localGCDocument, ActionCompiler.CompileType.ProcParam, "procedure parameters in \"" + ((XMLMessageIn) jGoObj).getFullName() + "\"", null));
                    } else {
                        ((XMLMessageIn) jGoObj).isDynamicProcedureCall = true;
                        giveInformation("Compilation messages for " + str1 + ".");
                        giveLightWarning("Unable to determine called procedure. Call parameters cannot be compiled until activation.");
                    }
                }
                if (((XMLMessageIn) jGoObj).isDynamicProcedureCall) {
                    giveInformation("Compilation messages for " + str1 + ".");
                    giveLightWarning("Unable to determine called procedure. Call parameters cannot be compiled until activation.");
                }
            }
        }
        if ((jGoObject instanceof XMLMessageOut)) {
            jGoObj = (XMLMessageOut) jGoObject;
            compileDocument2(((XMLMessageOut) jGoObj).myContentDocument, paramBoolean);
        }
        if ((jGoObject instanceof ConnectionPost)) {
            jGoObj = (ConnectionPost) jGoObject;
            ((ConnectionPost) jGoObj).remote = ((ConnectionPost) findObject(((ConnectionPost) jGoObj).remoteString));
        }
        if ((jGoObject instanceof ProcedureStep)) {
            jGoObj = (ProcedureStep) jGoObject;
            ((ProcedureStep) jGoObj).viewOwner = myCurrentView;
            ((ProcedureStep) jGoObj).node = ActionCompiler.compileStatement(((ProcedureStep) jGoObj).actionText, symbolTable, "actions in \"" + ((ProcedureStep) jGoObj).getFullName() + "\"", null);
            str1 = "procedure name in \"" + ((ProcedureStep) jGoObj).getFullName() + "\"";
            ((ProcedureStep) jGoObj).procNode = ((ProcCall) ActionCompiler.compile(((ProcedureStep) jGoObj).gp, symbolTable, ActionCompiler.CompileType.ProcCall, str1, null));
            if (((ProcedureStep) jGoObj).procNode != null) {
                localGrafcetProcedure = Utils.referencableToGrafcetProcedure(((ProcedureStep) jGoObj).procNode.getExpr().staticDecl(), false);
                if (localGrafcetProcedure != null) {
                    ((ProcedureStep) jGoObj).isDynamicProcedureCall = false;
                    ((ProcedureStep) jGoObj).procedure = localGrafcetProcedure;
                    localGCDocument = new GCDocument();
                    localGCDocument.copyFromCollection(localGrafcetProcedure.myContentDocument);
                    localGCDocument.owner = ((GrafcetObject) jGoObj);
                    ((ProcedureStep) jGoObj).paramNode = ((ProcParam) ActionCompiler.compile(((ProcedureStep) jGoObj).parameters, localGCDocument, ActionCompiler.CompileType.ProcParam, "procedure parameters in \"" + ((ProcedureStep) jGoObj).getFullName() + "\"", null));
                } else {
                    ((ProcedureStep) jGoObj).isDynamicProcedureCall = true;
                    giveInformation("Compilation messages for " + str1 + ".");
                    giveLightWarning("Unable to determine called procedure. Call parameters cannot be compiled until activation.");
                }
            }
        }
        if ((jGoObject instanceof DPWSObject)) {
            jGoObj = (DPWSObject) jGoObject;
            if (((DPWSObject) jGoObj).getBindingDeviceAddress().equals("")) {
                giveWarning("No binding specified for DPWS Object \"" + ((DPWSObject) jGoObj).getFullName() + "\".");
            } else if (((DPWSObject) jGoObj).getPortType() == null) {
                giveLightWarning("Device not present for DPWS Object \"" + ((DPWSObject) jGoObj).getFullName() + "\". Operation calls are not checked until executed.");
            }
        }
    }

    void setOutHack(GCDocument paramGCDocument1, GCDocument paramGCDocument2) {
        if (paramGCDocument2 != null) {
            paramGCDocument1.out = paramGCDocument2.out;
        }
        JGoListPosition localJGoListPosition = paramGCDocument1.getFirstObjectPos();
        for (JGoObject localJGoObject = paramGCDocument1.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = paramGCDocument1.getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof WorkspaceObject)) {
                WorkspaceObject localWorkspaceObject = (WorkspaceObject) localJGoObject;
                setOutHack(localWorkspaceObject.myContentDocument, localWorkspaceObject.getDocument());
            }
            localJGoListPosition = paramGCDocument1.getNextObjectPos(localJGoListPosition);
        }
    }

    /**
     * Compile tous le document
     * @param doc document à compiler
     * @param paramBoolean 
     */
    void compileDocument(GCCollection doc, boolean paramBoolean) {
        compileDocument1(doc);
        compileDocument2(doc, paramBoolean);
    }
    
    /**
     * Compile tous les objets de type group d'objet comme les macro du document
     * @param doc document à compiler
     */
    void compileDocument1(GCCollection doc) {
        ArrayList list = new ArrayList();
        JGoListPosition jGoPosition = doc.getFirstObjectPos();
        for (JGoObject jGoObject = doc.getObjectAtPos(jGoPosition); (jGoObject != null) && (jGoPosition != null); jGoObject = doc.getObjectAtPos(jGoPosition)) {
            if (!list.contains(jGoObject)) {
                compileJGoObject1(jGoObject, list);
            }
            jGoPosition = doc.getNextObjectPos(jGoPosition);
        }
    }

    /**
     * Compile tous les éléments de type unitaire comme les steps les transitions du document
     * @param doc document à compiler
     * @param paramBoolean 
     */
    void compileDocument2(GCCollection doc, boolean paramBoolean) {
        ArrayList localArrayList = new ArrayList();
        JGoListPosition localJGoListPosition = doc.getFirstObjectPos();
        for (JGoObject localJGoObject = doc.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = doc.getObjectAtPos(localJGoListPosition)) {
            if (!localArrayList.contains(localJGoObject)) {
                compileJGoObject2(localJGoObject, doc, paramBoolean, localArrayList);
            }
            localJGoListPosition = doc.getNextObjectPos(localJGoListPosition);
        }
    }

    void compileAction() {
        if (!objectHelp("Editor_ExecuteMenu_Compile")) {
            generateWarnings = true;
            eraseWarnings();
            GCDocument localGCDocument;
            for (localGCDocument = (GCDocument) myCurrentView.getDocument(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
            }
            compileDocument(localGCDocument);
            summarize();
            updateActions();
        }
    }

    void profile_compileAction() {
        if (!objectHelp("Editor_ExecuteMenu_Compile")) {
            generateWarnings = true;
            eraseWarnings();
            GCDocument localGCDocument;
            for (localGCDocument = (GCDocument) myCurrentView.getDocument(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
            }
            long l1 = 0L;
            for (int i = 0; i < 1; i++) {
                long l2 = System.nanoTime();
                compileDocument(localGCDocument);
                summarize();
                updateActions();
                long l3 = System.nanoTime() - l2;
                if (i == 0) {
                    l1 = l3;
                } else {
                    l1 = Math.min(l1, l3);
                }
            }
            System.out.println("Compilation time: " + l1);
        }
    }

    /**
     * Ajoute des noms par défaut aux étapes, macro étapes d'un document
     * @param paramGCDocument document dans lequel renommé les Etapes 
     */
    void addUniqueNames(GCDocument paramGCDocument, int oldI) {
        if (paramGCDocument != null) {
            int i = oldI;
            JGoListPosition localJGoListPosition = paramGCDocument.getFirstObjectPos();
            for (JGoObject localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition); 
                    (localJGoObject != null) && (localJGoListPosition != null); 
                    localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition)) {
                Object localObject;
                if ((localJGoObject instanceof GCStep)) {
                    localObject = (GCStep) localJGoObject;
                    if (((GCStep) localObject).getName().equals("")) {
                        ((GCStep) localObject).setName("#" + i);
                        i++;
                    }
                }
                if ((localJGoObject instanceof WorkspaceObject)) {
                    localObject = (WorkspaceObject) localJGoObject;
                    if (((WorkspaceObject) localObject).getName().equals("")) {
                        ((WorkspaceObject) localObject).setName("#" + i);
                    }
                    addUniqueNames(((WorkspaceObject) localObject).getContentDocument(), i*100);
                    i++;
                }
                if ((localJGoObject instanceof MacroStep)) {
                    localObject = (MacroStep) localJGoObject;
                    if (((MacroStep) localObject).getName().equals("")) {
                        ((MacroStep) localObject).setName("#" + i);                        
                    }
                    addUniqueNames(((MacroStep) localObject).getContentDocument(), i*100);
                    i++;
                }
                localJGoListPosition = paramGCDocument.getNextObjectPos(localJGoListPosition);
            }
        }
    }

    /**
     * Après compilation, on enlève les noms par défaut des étapes, macro étapes du document
     * @param doc document dans lequel renommé les Etapes 
     */
    void removeUniqueNames(GCDocument doc) {
        if (doc != null) {
            JGoListPosition position = doc.getFirstObjectPos();
            for (JGoObject localJGoObject = doc.getObjectAtPos(position); (localJGoObject != null) && (position != null); localJGoObject = doc.getObjectAtPos(position)) {
                Object localObject;
                if ((localJGoObject instanceof GCStep)) {
                    localObject = (GCStep) localJGoObject;
                    if (((GCStep) localObject).getName().startsWith("#")) {
                        ((GCStep) localObject).setName("");
                    }
                }
                if ((localJGoObject instanceof WorkspaceObject)) {
                    localObject = (WorkspaceObject) localJGoObject;
                    if (((WorkspaceObject) localObject).getName().startsWith("#")) {
                        ((WorkspaceObject) localObject).setName("");
                    }
                    removeUniqueNames(((WorkspaceObject) localObject).getContentDocument());
                }
                if ((localJGoObject instanceof MacroStep)) {
                    localObject = (MacroStep) localJGoObject;
                    if (((MacroStep) localObject).getName().startsWith("#")) {
                        ((MacroStep) localObject).setName("");
                    }
                    removeUniqueNames(((MacroStep) localObject).getContentDocument());
                }
                position = doc.getNextObjectPos(position);
            }
        }
    }

    void compileAllAction() {
        if (!objectHelp("Editor_ExecuteMenu_CompileAll")) {
            GCView localGCView = myCurrentView;
            generateWarnings = true;
            eraseWarnings();
            Iterator localIterator = topGrafcharts.getStorage().iterator();
            while (localIterator.hasNext()) {
                GCDocument localGCDocument = (GCDocument) localIterator.next();
                if (!localGCDocument.executing) {
                    localGCDocument.compiledOK = true;
                    myCurrentView = localGCDocument.getView();
                    compileDocument(localGCDocument);
                }
            }
            summarize();
            myCurrentView = localGCView;
            updateActions();
        }
    }

    void stopAction() {
        if (!objectHelp("Editor_ExecuteMenu_Stop")) {
            GCDocument localGCDocument;
            for (localGCDocument = (GCDocument) myCurrentView.getDocument(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
                if (((localGCDocument.owner instanceof MacroStep)) && (!(localGCDocument.owner instanceof ProcedureStep))) {
                    ((MacroStep) localGCDocument.owner).parentView.setDragDropEnabled(true);
                }
                if ((localGCDocument.owner instanceof WorkspaceObject)) {
                    ((WorkspaceObject) localGCDocument.owner).parentView.setDragDropEnabled(true);
                }
                if ((localGCDocument.owner instanceof XMLMessageIn)) {
                    ((XMLMessageIn) localGCDocument.owner).parentView.setDragDropEnabled(true);
                }
                if ((localGCDocument.owner instanceof XMLMessageOut)) {
                    ((XMLMessageOut) localGCDocument.owner).parentView.setDragDropEnabled(true);
                }
                if (((localGCDocument.owner instanceof GrafcetProcedure)) && (((GrafcetProcedure) localGCDocument.owner).parentView != null)) {
                    ((GrafcetProcedure) localGCDocument.owner).parentView.setDragDropEnabled(true);
                }
            }
            stopDocument(localGCDocument);
            updateActions();
        }
    }

    void stopAllAction() {
        if (!objectHelp("Editor_ExecuteMenu_StopAll")) {
            Iterator localIterator = topGrafcharts.getStorage().iterator();
            while (localIterator.hasNext()) {
                GCDocument localGCDocument = (GCDocument) localIterator.next();
                if (localGCDocument.executing) {
                    stopDocument(localGCDocument);
                }
            }
            updateActions();
        }
    }

    void shutDownAction() {
        if (!objectHelp("Editor_ExecuteMenu_ShutDownClient")) {
            myCurrentView.getDoc().shutdownSocket();
        }
        updateActions();
    }

    void debugStepAction() {
        if (!objectHelp("Editor_ExecuteMenu_Step")) {
            GCDocument localGCDocument = (GCDocument) myCurrentView.getDocument();
            stepDocument(localGCDocument);
            updateActions();
        }
    }

    void debugPauseAction() {
        if (!objectHelp("Editor_ExecuteMenu_Pause")) {
            GCDocument localGCDocument = (GCDocument) myCurrentView.getDocument();
            pauseDocument(localGCDocument);
            updateActions();
        }
    }

    void debugResumeAction() {
        if (!objectHelp("Editor_ExecuteMenu_Resume")) {
            GCDocument localGCDocument = (GCDocument) myCurrentView.getDocument();
            resumeDocument(localGCDocument);
            updateActions();
        }
    }

    /**
     * Cette method est appelé lorsqu'on clique sur le buton fermé de la fenètre
     * il affiche le message de confirmation et ferme si on le valid
     */
    void exitAction() {
        if (!objectHelp("Editor_FileMenu_Exit")) {
            int i = JOptionPane.showConfirmDialog(this, "All non-saved applications will be lost.\nReally exit?", "Exiting JGrafchart", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                if (connectedToBlasterServer) {
                    logoutBlasterAction();
                }
                setVisible(false);
                dispose();
                System.exit(0);
            }
        }
    }

    void showActionBlockAction() {
        GCStep localGCStep = (GCStep) this.selectedObject;
        localGCStep.showActionBlock();
        this.selectedObject = null;
        updateActions();
    }

    void hideActionBlockAction() {
        GCStep localGCStep = (GCStep) this.selectedObject;
        localGCStep.hideActionBlock();
        this.selectedObject = null;
        updateActions();
    }

    void stepEditAction() {
        GCStep localGCStep = (GCStep) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new StepDialog(localGCView.getFrame(), localGCStep, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void stepSetNameAction() {
        GCStep localGCStep = (GCStep) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new StepNameDialog(localGCView.getFrame(), localGCStep, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void transitionEditAction() {
        GenericTransition localGenericTransition = (GenericTransition) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new TransitionDialog(localGCView.getFrame(), localGenericTransition, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void transitionForceAction() {
        GenericTransition localGenericTransition = (GenericTransition) this.selectedObject;
        localGenericTransition.forced = true;
    }

    void showTransitionConditionAction() {
        GenericTransition localGenericTransition = (GenericTransition) this.selectedObject;
        localGenericTransition.showCondition();
        this.selectedObject = null;
        updateActions();
    }

    void hideTransitionConditionAction() {
        GenericTransition localGenericTransition = (GenericTransition) this.selectedObject;
        localGenericTransition.hideCondition();
        this.selectedObject = null;
        updateActions();
    }

    void procedureStepEditAction() {
        ProcedureStep localProcedureStep = (ProcedureStep) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new ProcedureStepDialog(localGCView.getFrame(), localProcedureStep, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    void procedureStepShowAction() {
        ProcedureStep localProcedureStep = (ProcedureStep) this.selectedObject;
        handleProcedureStep(localProcedureStep);
    }

    void processStepShowAction() {
        CallSource localCallSource = (CallSource) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new ProcedureCallsDialog(localGCView.getFrame(), localCallSource, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    void grafcetProcedureParametersAction() {
        GrafcetProcedure localGrafcetProcedure = (GrafcetProcedure) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new ProcedureParameters(localGCView.getFrame(), localGrafcetProcedure, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    void grafcetProcedureStartAction() {
        GrafcetProcedure localGrafcetProcedure = (GrafcetProcedure) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new StartParameters(localGCView.getFrame(), localGrafcetProcedure, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    void grafcetProcedureCallsAction() {
        GrafcetProcedure localGrafcetProcedure = (GrafcetProcedure) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new ProcedureCallsDialog(localGCView.getFrame(), localGrafcetProcedure, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    void grafcetProcedureBodyAction() {
        final GrafcetProcedure localGrafcetProcedure = (GrafcetProcedure) this.selectedObject;
        if (localGrafcetProcedure.frame == null) {
            localGrafcetProcedure.myContentDocument.setName(localGrafcetProcedure.myName.getText());
            MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localGrafcetProcedure.myName.getText(), true);
            localMyJInternalFrame.setDefaultCloseOperation(2);
            final GCView localGCView = new GCView(localGrafcetProcedure.myContentDocument);
            localGrafcetProcedure.myContentDocument.setView(localGCView);
            localGCView.setStepCounter(localGrafcetProcedure.stepCounterInt);
            localGCView.initialize(this, localMyJInternalFrame);
            localGCView.layer = (myCurrentView.layer + 1);
            localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = localGCView;
                    Editor.topLevelView = false;
                    localGCView.requestFocus();
                    AppAction.updateAllActions();
                }

                public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = localGrafcetProcedure.parentView;
                    localGrafcetProcedure.rgbColor = localGrafcetProcedure.myContentDocument.getPaperColor().getRGB();
                    localGrafcetProcedure.bounds = localGrafcetProcedure.frame.getBounds();
                    localGrafcetProcedure.myContentDocument.currentScale = localGrafcetProcedure.view.getScale();
                    localGrafcetProcedure.point = localGrafcetProcedure.view.getViewPosition();
                    localGrafcetProcedure.frame = null;
                    localGrafcetProcedure.view = null;
                    AppAction.updateAllActions();
                }

                public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }
            });
            localGrafcetProcedure.parentView = myCurrentView;
            localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
            localMyJInternalFrame.getContentPane().add(localGCView);
            getDesktop().add(localMyJInternalFrame, new Integer(localGCView.layer));
            if (localGrafcetProcedure.bounds == null) {
                localMyJInternalFrame.setSize(400, 400);
                Point localPoint = localGrafcetProcedure.getLocation();
                myCurrentView.convertDocToView(localPoint);
                localMyJInternalFrame.setLocation((int) localPoint.getX() + 10, (int) localPoint.getY() + 40);
            } else {
                localGrafcetProcedure.myContentDocument.setPaperColor(new Color(localGrafcetProcedure.rgbColor));
                localGCView.setScale(localGrafcetProcedure.myContentDocument.currentScale);
                if (localGrafcetProcedure.point != null) {
                    localGCView.setViewPosition(localGrafcetProcedure.point);
                }
                localMyJInternalFrame.setBounds(localGrafcetProcedure.bounds);
            }
            localMyJInternalFrame.show();
            localGCView.initializeDragDropHandling();
            myCurrentView = localGCView;
            localGrafcetProcedure.frame = localMyJInternalFrame;
            localGrafcetProcedure.view = localGCView;
            topLevelView = false;
            localGCView.requestFocus();
            AppAction.updateAllActions();
        } else if (!localGrafcetProcedure.frame.isShowing()) {
            if (localGrafcetProcedure.frame.isIcon()) {
                try {
                    localGrafcetProcedure.frame.setIcon(false);
                } catch (Exception localException1) {
                }
            }
            localGrafcetProcedure.frame.show();
            localGrafcetProcedure.view.requestFocus();
        } else {
            localGrafcetProcedure.rgbColor = localGrafcetProcedure.myContentDocument.getPaperColor().getRGB();
            localGrafcetProcedure.bounds = localGrafcetProcedure.frame.getBounds();
            localGrafcetProcedure.myContentDocument.currentScale = localGrafcetProcedure.view.getScale();
            localGrafcetProcedure.point = localGrafcetProcedure.view.getViewPosition();
            localGrafcetProcedure.stepCounterInt = localGrafcetProcedure.view.getStepCounter();
            try {
                localGrafcetProcedure.frame.setClosed(true);
            } catch (Exception localException2) {
            }
            localGrafcetProcedure.setDraggable(true);
            localGrafcetProcedure.frame = null;
            localGrafcetProcedure.view = null;
            myCurrentView = localGrafcetProcedure.parentView;
            myCurrentView.requestFocus();
            AppAction.updateAllActions();
        }
    }

    void grafcetClassBodyAction() {
        final GCClass localGCClass = (GCClass) this.selectedObject;
        if (localGCClass.frame == null) {
            localGCClass.myContentDocument.setName(localGCClass.myLabel.getText());
            MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localGCClass.myLabel.getText(), true);
            localMyJInternalFrame.setDefaultCloseOperation(2);
            final GCView localGCView = new GCView(localGCClass.myContentDocument);
            localGCView.setStepCounter(localGCClass.stepCounterInt);
            localGCView.initialize(this, localMyJInternalFrame);
            localGCView.layer = (myCurrentView.layer + 1);
            localGCClass.myContentDocument.setPaperColor(new Color(255, 255, 255));
            localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = localGCView;
                    Editor.topLevelView = false;
                    localGCView.requestFocus();
                    AppAction.updateAllActions();
                }

                public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = localGCClass.parentView;
                    localGCClass.rgbColor = localGCClass.myContentDocument.getPaperColor().getRGB();
                    localGCClass.bounds = localGCClass.frame.getBounds();
                    localGCClass.currentScale = localGCClass.view.getScale();
                    localGCClass.point = localGCClass.view.getViewPosition();
                    localGCClass.frame = null;
                    localGCClass.view = null;
                    AppAction.updateAllActions();
                }

                public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }
            });
            localGCClass.parentView = myCurrentView;
            localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
            localMyJInternalFrame.getContentPane().add(localGCView);
            getDesktop().add(localMyJInternalFrame, new Integer(localGCView.layer));
            if (localGCClass.bounds == null) {
                localMyJInternalFrame.setSize(400, 600);
                Point localPoint = localGCClass.getLocation();
                localMyJInternalFrame.setLocation((int) localPoint.getX() + 130, (int) localPoint.getY() - 70);
            } else {
                localGCClass.myContentDocument.setPaperColor(new Color(localGCClass.rgbColor));
                localGCView.setScale(localGCClass.currentScale);
                if (localGCClass.point != null) {
                    localGCView.setViewPosition(localGCClass.point);
                }
                localMyJInternalFrame.setBounds(localGCClass.bounds);
            }
            Point localPoint = localGCClass.getLocation();
            localMyJInternalFrame.setLocation((int) localPoint.getX() + 130, (int) localPoint.getY() - 70);
            localMyJInternalFrame.show();
            localGCView.initializeDragDropHandling();
            myCurrentView = localGCView;
            localGCClass.frame = localMyJInternalFrame;
            localGCClass.view = localGCView;
            topLevelView = false;
            localGCView.requestFocus();
            AppAction.updateAllActions();
        } else if (!localGCClass.frame.isShowing()) {
            if (localGCClass.frame.isIcon()) {
                try {
                    localGCClass.frame.setIcon(false);
                } catch (Exception localException1) {
                }
            }
            localGCClass.frame.show();
            localGCClass.view.requestFocus();
        } else {
            localGCClass.rgbColor = localGCClass.myContentDocument.getPaperColor().getRGB();
            localGCClass.bounds = localGCClass.frame.getBounds();
            localGCClass.currentScale = localGCClass.view.getScale();
            localGCClass.point = localGCClass.view.getViewPosition();
            localGCClass.stepCounterInt = localGCClass.view.getStepCounter();
            try {
                localGCClass.frame.setClosed(true);
            } catch (Exception localException2) {
            }
            localGCClass.setDraggable(true);
            localGCClass.frame = null;
            localGCClass.view = null;
            myCurrentView = localGCClass.parentView;
            myCurrentView.requestFocus();
            AppAction.updateAllActions();
        }
    }

    void grafcetClassInstantiateAction() {
        GCClass localGCClass = (GCClass) this.selectedObject;
        if (localGCClass != null) {
            localGCClass.instantiate();
        }
        AppAction.updateAllActions();
    }

    void setSuperClassAction() {
        GCClass localGCClass = (GCClass) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new SetSuperClassDialog(localGCView.getFrame(), localGCClass, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    void grafcetClassUpdateAction() {
        GCClass localGCClass = (GCClass) this.selectedObject;
        if (localGCClass != null) {
            localGCClass.updateInstances();
        }
        AppAction.updateAllActions();
    }

    private JGoText newPaletteText(int paramInt1, int paramInt2, String paramString) {
        JGoText localJGoText = new JGoText(new Point(paramInt1, paramInt2), 12, paramString, "SansSerif", true, false, false, 2, true, false);
        localJGoText.setTransparent(true);
        localJGoText.setSelectable(false);
        return localJGoText;
    }

    /**
     * Ajoute un nouvel élément à la palette
     * @param paramJGoDocument
     * @param paramInt
     * @param paramJGoObject
     * @param paramString
     * @return 
     */
    private int addToPalette(JGoDocument paramJGoDocument, int paramInt, JGoObject paramJGoObject, String paramString) {
        return addToPalette(paramJGoDocument, paramInt, paramJGoObject, paramString, 0);
    }

    /**
     * Ajoute un nouvel élément à la palette
     * 
     * @param paramJGoDocument JGoDocument lié au à la palette
     * @param y
     * @param paramJGoObject
     * @param name
     * @param widthOffset
     * @return 
     */
    private int addToPalette(JGoDocument paramJGoDocument, int y, JGoObject paramJGoObject, String name, int widthOffset) {
        paramJGoObject.setTopLeft(55 - paramJGoObject.getWidth() / 2 + widthOffset, y);
        paramJGoDocument.addObjectAtTail(paramJGoObject);
        y += paramJGoObject.getHeight();
        y += 5;
        if (name != null) {
            JGoText localJGoText = newPaletteText(55, y, name);
            paramJGoDocument.addObjectAtTail(localJGoText);
            y += localJGoText.getHeight();
            y += 35;
        }
        return y;
    }

    /**
     * Initialisation de la palette des éléments de G7
     */
    public void initPaletteFC() {
        int yPos = 20; //Position de départ dans la palette
        JGoDocument localJGoDocument = this.myPaletteFC.getDocument();
        localJGoDocument.setSuspendUpdates(true);
        yPos = addToPalette(localJGoDocument, yPos, new GCStepInitial(new Point(), ""), "Initial Step", -10);
        yPos = addToPalette(localJGoDocument, yPos, new GCStep(new Point(), ""), "Step", -10);
        yPos = addToPalette(localJGoDocument, yPos, new GCTransition(new Point(), "0", ""), "Transition", 10);
        yPos = addToPalette(localJGoDocument, yPos, new ParallelSplit(new Point()), "Parallel Split");
        yPos = addToPalette(localJGoDocument, yPos, new ParallelJoin(new Point()), "Parallel Join");
        //yPos = addToPalette(localJGoDocument, yPos, new WorkspaceObject(new Point(), "W1"), "Workspace Object");
        MacroStep localMacroStep = new MacroStep(new Point(), "");
        localMacroStep.myContentDocument.addObjectAtHead(new EnterStep(new Point(200, 70), ""));
        localMacroStep.myContentDocument.addObjectAtHead(new ExitStep(new Point(200, 200), ""));
        yPos = addToPalette(localJGoDocument, yPos, localMacroStep, "Macro Step", -10);
        yPos = addToPalette(localJGoDocument, yPos, new EnterStep(new Point(), ""), "Enter Step", -10);
        yPos = addToPalette(localJGoDocument, yPos, new ExitStep(new Point(), ""), "Exit Step", -10);
        //yPos = addToPalette(localJGoDocument, yPos, new ExceptionTransition(new Point(), "0", ""), "Exception Trans.");
        //if (!labVersion) {
        if (false) {
            GrafcetProcedure localGrafcetProcedure = new GrafcetProcedure(new Point(), "Proc1");
            localGrafcetProcedure.myContentDocument.addObjectAtHead(new EnterStep(new Point(200, 70), "S1"));
            localGrafcetProcedure.myContentDocument.addObjectAtHead(new ExitStep(new Point(200, 200), "S2"));
            yPos = addToPalette(localJGoDocument, yPos, localGrafcetProcedure, "Procedure");
            yPos = addToPalette(localJGoDocument, yPos, new ProcedureStep(new Point(), "P1"), "Procedure Step", 10);
            yPos = addToPalette(localJGoDocument, yPos, new ProcessStep(new Point(), "P1"), "Process Step", 10);
            yPos = addToPalette(localJGoDocument, yPos, new ConnectionPostIn(new Point(), "CPIn"), null, -5);
            yPos = addToPalette(localJGoDocument, yPos, new ConnectionPostOut(new Point(), "CPOut"), "Conn. Posts");
            yPos = addToPalette(localJGoDocument, yPos, new StepFusionSet(new Point(), "SF1"), "Step Fusion Set");
        }
        localJGoDocument.setSuspendUpdates(false);
    }

    public void initPaletteVar() {
        int i = 20;
        JGoDocument localJGoDocument = this.myPaletteVar.getDocument();
        localJGoDocument.setSuspendUpdates(true);
        i = addToPalette(localJGoDocument, i, new BooleanVariable(new Point()), "Boolean Variable");
        i = addToPalette(localJGoDocument, i, new IntegerVariable(new Point()), "Integer Variable");
        i = addToPalette(localJGoDocument, i, new RealVariable(new Point()), "Real Variable");
        i = addToPalette(localJGoDocument, i, new StringVariable(new Point()), "String variable");
        if (!labVersion) {
            i = addToPalette(localJGoDocument, i, new BooleanList(new Point()), "Boolean List");
            i = addToPalette(localJGoDocument, i, new IntegerList(new Point()), "Integer List");
            i = addToPalette(localJGoDocument, i, new RealList(new Point()), "Real List");
            i = addToPalette(localJGoDocument, i, new StringList(new Point()), "String List");
        }
        localJGoDocument.setSuspendUpdates(false);
    }

    public void initPaletteIO() {
        int i = 20;
        JGoDocument localJGoDocument = this.myPaletteIO.getDocument();
        localJGoDocument.setSuspendUpdates(true);
        i = addToPalette(localJGoDocument, i, new DigitalIn(new Point()), "Digital Input");
        i = addToPalette(localJGoDocument, i, new DigitalIn1(new Point()), "Digital Input\n(Inverse Logic)");
        i = addToPalette(localJGoDocument, i, new DigitalOut(new Point(), "0"), "Digital Output");
        i = addToPalette(localJGoDocument, i, new DigitalOut1(new Point(), "0"), "Digital Output\n(Inverse Logic)");
        i = addToPalette(localJGoDocument, i, new AnalogIn(new Point()), "Analog Input");
        i = addToPalette(localJGoDocument, i, new AnalogOut(new Point(), "0.0"), "Analog Output");
        i = addToPalette(localJGoDocument, i, new SocketBooleanIn(new Point()), "Socket Bool In");
        i = addToPalette(localJGoDocument, i, new SocketIntIn(new Point()), "Socket Int In");
        i = addToPalette(localJGoDocument, i, new SocketRealIn(new Point()), "Socket Real In");
        i = addToPalette(localJGoDocument, i, new SocketStringIn(new Point()), "Socket String In");
        i = addToPalette(localJGoDocument, i, new SocketBoolOut(new Point()), "Socket Bool Out");
        i = addToPalette(localJGoDocument, i, new SocketIntOut(new Point()), "Socket Int Out");
        i = addToPalette(localJGoDocument, i, new SocketRealOut(new Point()), "Socket Real Out");
        i = addToPalette(localJGoDocument, i, new SocketStringOut(new Point()), "Socket String Out");
        if (!labVersion) {
            i = addToPalette(localJGoDocument, i, new ByteStreamIn(new Point()), "ByteStream In");
            i = addToPalette(localJGoDocument, i, new ByteStreamOut(new Point()), "ByteStream Out");
            i = addToPalette(localJGoDocument, i, new DPWSObject(new Point()), "DPWS Object");
            i = addToPalette(localJGoDocument, i, new LabCommObject(new Point()), "LabComm Object");
        }
        localJGoDocument.setSuspendUpdates(false);
    }

    public void initPaletteXML() {
        int i = 20;
        JGoDocument localJGoDocument = this.myPaletteXML.getDocument();
        localJGoDocument.setSuspendUpdates(true);
        i = addToPalette(localJGoDocument, i, new XMLMessageIn(new Point(), "In1"), "XML Message In");
        i = addToPalette(localJGoDocument, i, new XMLMessageOut(new Point(), "Out1"), "XML Message Out");
        i = addToPalette(localJGoDocument, i, new IntegerAttributeVariable(new Point()), "Integer Attribute");
        i = addToPalette(localJGoDocument, i, new RealAttributeVariable(new Point()), "Real Attribute");
        i = addToPalette(localJGoDocument, i, new StringAttributeVariable(new Point()), "String Attribute");
        i = addToPalette(localJGoDocument, i, new IndexVariable(new Point()), "Index Variable");
        localJGoDocument.setSuspendUpdates(false);
    }

    public void initPaletteGUI() {
        int i = 20;
        JGoDocument localJGoDocument = this.myPaletteGUI.getDocument();
        localJGoDocument.setSuspendUpdates(true);
        FreeText localFreeText = new FreeText(new Point(), 12, "ABC", "SansSerif", true, false, false, 1, false, true);
        localFreeText.setTransparent(true);
        localFreeText.setResizable(true);
        localFreeText.setEditOnSingleClick(true);
        i = addToPalette(localJGoDocument, i, localFreeText, null);
        localFreeText = new FreeText(new Point(), 16, "ABC", "SansSerif", true, false, false, 1, false, true);
        localFreeText.setTransparent(true);
        localFreeText.setResizable(true);
        localFreeText.setEditOnSingleClick(true);
        i = addToPalette(localJGoDocument, i, localFreeText, null);
        localFreeText = new FreeText(new Point(), 20, "ABC", "SansSerif", true, false, false, 1, false, true);
        localFreeText.setTransparent(true);
        localFreeText.setResizable(true);
        localFreeText.setEditOnSingleClick(true);
        i = addToPalette(localJGoDocument, i, localFreeText, "Text");
        GCRectangle localGCRectangle = new GCRectangle(new Point(), new Dimension(50, 50));
        localGCRectangle.setBrush(JGoBrush.makeStockBrush(Color.lightGray));
        i = addToPalette(localJGoDocument, i, localGCRectangle, "Rectangle");
        GCEllipse localGCEllipse = new GCEllipse(new Point(), new Dimension(50, 50));
        localGCEllipse.setBrush(JGoBrush.makeStockBrush(Color.lightGray));
        i = addToPalette(localJGoDocument, i, localGCEllipse, "Ellipse");
        
        Icon icon = new Icon(new Point(), new Dimension());
        icon.loadImage(getIconLib() + "/graphics/icon.gif");
        icon.setNormalSize();
        i = addToPalette(localJGoDocument, i, icon, "Icon");
        
        Object localObject2;
        Object localObject3;
        Object localObject4;
        //if (!labVersion) {
        if (false) {
            GCPolygon localObject1 = new GCPolygon();
            ((GCPolygon) localObject1).setBrush(JGoBrush.makeStockBrush(Color.lightGray));
            ((GCPolygon) localObject1).setPen(JGoPen.make(65535, 3, Color.gray));
            localObject2 = new Point();
            ((GCPolygon) localObject1).addPoint(new Point(((Point) localObject2).x + 15, ((Point) localObject2).y + 0));
            ((GCPolygon) localObject1).addPoint(new Point(((Point) localObject2).x + 30, ((Point) localObject2).y + 0));
            ((GCPolygon) localObject1).addPoint(new Point(((Point) localObject2).x + 45, ((Point) localObject2).y + 15));
            ((GCPolygon) localObject1).addPoint(new Point(((Point) localObject2).x + 45, ((Point) localObject2).y + 30));
            ((GCPolygon) localObject1).addPoint(new Point(((Point) localObject2).x + 30, ((Point) localObject2).y + 45));
            ((GCPolygon) localObject1).addPoint(new Point(((Point) localObject2).x + 15, ((Point) localObject2).y + 45));
            ((GCPolygon) localObject1).addPoint(new Point(((Point) localObject2).x + 0, ((Point) localObject2).y + 30));
            ((GCPolygon) localObject1).addPoint(new Point(((Point) localObject2).x + 0, ((Point) localObject2).y + 15));
            i = addToPalette(localJGoDocument, i, (JGoObject) localObject1, "Polygon");
            localObject3 = new GC3DRect(new Point(), new Dimension(50, 50));
            ((GC3DRect) localObject3).setBrush(JGoBrush.makeStockBrush(Color.lightGray));
            i = addToPalette(localJGoDocument, i, (JGoObject) localObject3, "3D Rectangle");
            this.drawStrokeButton = new JGoButton(new Point(), new Dimension(65, 50), "Line");
            i = addToPalette(localJGoDocument, i, this.drawStrokeButton, "Line Button");
            this.drawSplineButton = new JGoButton(new Point(), new Dimension(75, 50), "Spline");
            i = addToPalette(localJGoDocument, i, this.drawSplineButton, "Spline Button");
            localObject4 = new Icon(new Point(), new Dimension());
            ((Icon) localObject4).loadImage(getIconLib() + "/graphics/icon.gif");
            ((Icon) localObject4).setNormalSize();
            i = addToPalette(localJGoDocument, i, (JGoObject) localObject4, "Icon");
        }
        /*
        Object localObject1 = new AssignmentButton(new Point());
        ((AssignmentButton) localObject1).modifySize();
        i = addToPalette(localJGoDocument, i, (JGoObject) localObject1, "Action Button");
        */
        //if (!labVersion) {
        if (false) {
            localObject2 = new GraphicalButton(new Point(), new Dimension(70, 70));
            ((GraphicalButton) localObject2).loadImage("../doc/tutorial/button10.gif");
            ((GraphicalButton) localObject2).setSize(((GraphicalButton) localObject2).getNaturalSize());
            i = addToPalette(localJGoDocument, i, (JGoObject) localObject2, "Graphical Action\nButton");
            i = addToPalette(localJGoDocument, i, new SFCPlotter(new Point(), "Plot1"), "Plotter");
            localObject3 = new Browser(new Dimension(70, 50));
            i = addToPalette(localJGoDocument, i, (JGoObject) localObject3, "Browser");
            i = addToPalette(localJGoDocument, i, new ShowWorkspaceButton(new Point()), "Show Workspace\nButton");
            localObject4 = new ChemIcon(new Point(), new Dimension(60, 85));
            i = addToPalette(localJGoDocument, i, (JGoObject) localObject4, "CHEM Icon");
            LUCASLogo localLUCASLogo = new LUCASLogo(new Dimension(80, 85));
            i = addToPalette(localJGoDocument, i, localLUCASLogo, "LUCAS Icon");
        }
        localJGoDocument.setSuspendUpdates(false);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] paramArrayOfString) {
        //Recupère le Look and Fell de des des UI        
        //if (getArg(paramArrayOfString, "-windows", "0").equals("1")) {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    System.out.println(info.getName());
                    if ("GTK+".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        //break;
                    }
                }
                UIManager.setLookAndFeel(new WindowsLookAndFeel());
            } catch (UnsupportedLookAndFeelException localUnsupportedLookAndFeelException) {
                Utils.writeError("Unsupported look and feel: " + localUnsupportedLookAndFeelException.getMessage());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}
        
        
        
        
        labVersion = getArg(paramArrayOfString, "-plain", "0").equals("1");
        //Paramètres réseaux
        loginName = getArg(paramArrayOfString, "-name", "JGrafchart");
        port = getArg(paramArrayOfString, "-port", "3412");
        serverIP = getArg(paramArrayOfString, "-serverIP", "127.0.0.1");
        graphicalSteps = getArg(paramArrayOfString, "-graphicalSteps", "0").equals("1");
        //Definition du module d'I∕O
        String str1 = getArg(paramArrayOfString, "-localIO", "grafchart.sfc.io.lund.LundLocalIO");
        try {
            Class localClass = Class.forName(str1);
            localIO = (LocalIO) localClass.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException localInstantiationException) {
            Utils.writeException(localInstantiationException);
        }
        enableUndo = Args.getArg(paramArrayOfString, "-undo");
        debugMode = Args.getArg(paramArrayOfString, "-debug");
        Editor localEditor = new Editor(paramArrayOfString);
        singleton = localEditor;
        try {
            String str2 = getArg(paramArrayOfString, "-load", "none");
            if (!str2.equals("none")) {
                localEditor.openWorkspace(str2);
            }
        } catch (Exception localException1) {
            Utils.writeException(localException1);
        }
        GCDocument localGCDocument;
        try {
            String str3 = getArg(paramArrayOfString, "-loadcompile", "none");
            if (!str3.equals("none")) {
                localGCDocument = localEditor.openWorkspace(str3);
                localEditor.compileWorkspace(localGCDocument);
            }
        } catch (Exception localException2) {
            Utils.writeException(localException2);
        }
        try {
            String str4 = getArg(paramArrayOfString, "-loadcompilestart", "none");
            if (!str4.equals("none")) {
                localGCDocument = localEditor.openWorkspace(str4);
                boolean bool = localEditor.compileWorkspace(localGCDocument);
                if (bool) {
                    localEditor.startWorkspace(localGCDocument);
                }
            }
        } catch (Exception localException3) {
            Utils.writeException(localException3);
        }
        if (getArg(paramArrayOfString, "-connect", "0").equals("1")) {
            initBlasterAction();
        }
        updateActions();
    }

    /**
     * Recupère le paramètre demandé et retourne  la valeur par defaut si il n'existe pas. 
     * @param paramArray liste de paramètres
     * @param paramName Nom du paramètre recherché
     * @param defaut Valeur de retour par defaut
     * @return Valeur du paramètre ou valeur par défaut
     *
    */
    private static String getArg(String[] paramArray, String paramName, String defaut) {
        try {
            return Args.getArg(paramArray, paramName, defaut);
        } catch (JUtilsException localJUtilsException) {
            Utils.writeException(localJUtilsException);
            System.exit(1);
        }
        return null;
    }

    public GCView getCurrentView() {
        return myCurrentView;
    }

    public static void updateActions() {
        AppAction.updateAllActions();
    }

    public void processViewChange(JGoViewEvent paramJGoViewEvent) {
        Object localObject;
        switch (paramJGoViewEvent.getHint()) {
            case 1:
            case 20:
                updateActions();
                break;
            case 21:
                updateActions();
                break;
            case 23:
                for (localObject = paramJGoViewEvent.getJGoObject(); (((JGoObject) localObject).getParent() != null) && ((!(((JGoObject) localObject).getParent() instanceof GCGroup)) || (((GCGroup) ((JGoObject) localObject).getParent()).isObjectified())); localObject = ((JGoObject) localObject).getParent()) {
                }
                if ((!(localObject instanceof AssignmentButton)) && (!(localObject instanceof GraphicalButton)) && (!(localObject instanceof DigitalIn))) {
                    this.selectedObject = ((JGoObject) localObject);
                    updateActions();
                    callDialog((JGoObject) localObject, paramJGoViewEvent.getPointViewCoords());
                }
                break;
            case 22:
                localObject = paramJGoViewEvent.getJGoObject();
                if (objectHelpMode) {
                    callDialog((JGoObject) localObject, paramJGoViewEvent.getPointViewCoords());
                    paramJGoViewEvent.consume();
                } else if ((localObject instanceof ShowWorkspaceButton)) {
                    if (slideMode) {
                        ((ShowWorkspaceButton) localObject).showWorkspace();
                    }
                } else if (((localObject instanceof JGoText)) && ((((JGoObject) localObject).getParent() instanceof Browser))) {
                    Browser localBrowser = (Browser) ((JGoObject) localObject).getParent();
                    localBrowser.setSelected((JGoText) localObject);
                } else if (((localObject instanceof JGoText)) && ((((JGoObject) localObject).getParent() instanceof GenericTransition)) && (localObject == ((GenericTransition) ((JGoObject) localObject).getParent()).myCondition)) {
                    this.selectedObject = ((JGoObject) localObject).getParent();
                    if (!objectHelpMode) {
                        transitionEditAction();
                    }
                } else if (localObject == this.drawStrokeButton) {
                    if (myCurrentView != null) {
                        myCurrentView.toggleRoutingLink(false);
                    }
                } else if (localObject == this.drawSplineButton) {
                    if (myCurrentView != null) {
                        myCurrentView.toggleRoutingLink(true);
                    }
                } else if (((localObject instanceof JGoText)) && ((((JGoObject) localObject).getParent() instanceof GCStep)) && (localObject == ((GCStep) ((JGoObject) localObject).getParent()).myActions)) {
                    this.selectedObject = ((JGoObject) localObject).getParent();
                    stepEditAction();
                }
                break;
        }
    }

    /**
     * Lance le menu contextuel du jGoObject aux coordonées spécifiées
     * @param jGoObject Objet dont on veut ouvrir le menu contextuel
     * @param coord Coordonées du menu
     */
    public void callDialog(JGoObject jGoObject, Point coord) {
        //System.out.println("Call dialog");
        Object localObject1;
        Object localObject2;
        if (objectHelpMode) {
            if ((jGoObject instanceof JGoButton)) {
                localObject1 = (JGoButton) jGoObject;
                localObject2 = "";
                if (localObject1 == this.drawStrokeButton) {
                    localObject2 = "LangRef_GUI_Line";
                } else if (localObject1 == this.drawSplineButton) {
                    localObject2 = "LangRef_GUI_Spline";
                }
                objectHelp((String) localObject2);
            } else if ((jGoObject instanceof Helpable)) {
                localObject1 = (Helpable) jGoObject;
                localObject2 = ((Helpable) localObject1).getHelpID();
                if (!((String) localObject2).equals("")) {
                    objectHelp((String) localObject2);
                } else {
                    Utils.writeInternalError("No help id for " + jGoObject.getClass());
                }
            } else if ((jGoObject instanceof JGoText)) {
                this.selectedObject = jGoObject.getParent();
                callDialog(this.selectedObject, coord);
            } else {
                Utils.writeInternalError("No help for " + jGoObject.getClass());
            }
        } else {
            if ((jGoObject instanceof GCGroup)) {
                localObject1 = (Referencable) this.selectedObject;
                localObject2 = myCurrentView;
                if (localObject2 != null) {
                    new DrawableNameDialog(((GCView) localObject2).getFrame(), ((GCView) localObject2).getDoc(), (Referencable) localObject1, (GCView) localObject2).setVisible(true);
                }
                this.selectedObject = null;
            }
            if (((jGoObject instanceof GCStep)) && (myCurrentView != null)) {
                this.contextStep.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if ((jGoObject instanceof GenericTransition)) {
                if (!slideMode) {
                    if (myCurrentView != null) {
                        this.contextTransition.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
                    }
                } else {
                    transitionForceAction();
                }
            }
            if (((jGoObject instanceof MacroStep)) && (!(jGoObject instanceof ProcedureStep)) && (myCurrentView != null)) {
                this.contextMacroStep.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if (((jGoObject instanceof GrafcetProcedure)) && (myCurrentView != null)) {
                this.contextProcedure.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if (((jGoObject instanceof StepFusionSet)) && (myCurrentView != null)) {
                this.contextStepFusionSet.show(myCurrentView, coord.x, coord.y);
            }
            if (((jGoObject instanceof SFCPlotter)) && (myCurrentView != null)) {
                this.contextSFCPlotter.show(myCurrentView, coord.x, coord.y);
            }
            if (((jGoObject instanceof WorkspaceObject)) && (!(jGoObject instanceof StepFusionSet))) {
                localObject1 = (WorkspaceObject) jGoObject;
                if (slideMode) {
                    handleWorkspaceObject((WorkspaceObject) localObject1);
                    this.selectedObject = null;
                    AppAction.updateAllActions();
                } else if (myCurrentView != null) {
                    this.contextWorkspaceObject.show(myCurrentView, coord.x, coord.y);
                }
            }
            if (((jGoObject instanceof GCClass)) && (myCurrentView != null)) {
                this.contextGCClas.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if ((jGoObject instanceof AssignmentButton)) {
                if (myCurrentView != null) {
                    new AssignmentButtonDialog(myCurrentView.getFrame(), myCurrentView.getDoc(), (AssignmentButton) jGoObject, myCurrentView).setVisible(true);
                }
                this.selectedObject = null;
            }
            if ((jGoObject instanceof GraphicalButton)) {
                if (myCurrentView != null) {
                    new GraphicalButtonDialog(myCurrentView.getFrame(), myCurrentView.getDoc(), (GraphicalButton) jGoObject, myCurrentView).setVisible(true);
                }
                this.selectedObject = null;
            }
            if (((jGoObject instanceof ProcedureStep)) && (!(jGoObject instanceof ProcessStep)) && (myCurrentView != null)) {
                this.contextProcedureStep.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if (((jGoObject instanceof ProcessStep)) && (myCurrentView != null)) {
                this.contextProcessStep.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if ((jGoObject instanceof ConnectionPost)) {
                if (myCurrentView != null) {
                    new ConnectionPostDialog(myCurrentView.getFrame(), myCurrentView.getDoc(), (ConnectionPost) jGoObject, myCurrentView).setVisible(true);
                }
                this.selectedObject = null;
            }
            if ((jGoObject instanceof BasicList)) {
                if (myCurrentView != null) {
                    new BasicListDialog(myCurrentView.getFrame(), (BasicList) jGoObject, myCurrentView).setVisible(true);
                }
                this.selectedObject = null;
            }
            if ((jGoObject instanceof InputVariable)) {
                if (myCurrentView != null) {
                    new InputDialog(myCurrentView.getFrame(), (InputVariable) jGoObject, myCurrentView).setVisible(true);
                }
                this.selectedObject = null;
            }
            if ((jGoObject instanceof InternalVariable)) {
                if (myCurrentView != null) {
                    new InternalVariableDialog(myCurrentView.getFrame(), (InternalVariable) jGoObject, myCurrentView).setVisible(true);
                }
                this.selectedObject = null;
            }
            if ((jGoObject instanceof FreeText)) {
                if (myCurrentView != null) {
                    this.contextText.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
                }
            } else if ((jGoObject instanceof JGoText)) {
                this.selectedObject = jGoObject.getParent();
                AppAction.updateAllActions();
                callDialog(this.selectedObject, coord);
            }
            if ((jGoObject instanceof SocketIn)) {
                if (myCurrentView != null) {
                    localObject1 = (SocketIn) jGoObject;
                    localObject2 = myCurrentView;
                    new SocketInDialog(((GCView) localObject2).getFrame(), (SocketIn) localObject1, (GCView) localObject2).setVisible(true);
                }
                this.selectedObject = null;
                AppAction.updateAllActions();
            }
            if (((jGoObject instanceof ByteStreamIn)) && (myCurrentView != null)) {
                this.contextByteStreamIn.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if (((jGoObject instanceof ByteStreamOut)) && (myCurrentView != null)) {
                this.contextByteStreamOut.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if (((jGoObject instanceof DPWSObject)) && (myCurrentView != null)) {
                new DPWSObjectDialog(myCurrentView.getFrame(), (DPWSObject) jGoObject, myCurrentView).setVisible(true);
            }
            if (((jGoObject instanceof LabCommObject)) && (myCurrentView != null)) {
                this.contextLabCommObject.show(myCurrentView, coord.x, coord.y);
            }
            if ((jGoObject instanceof SocketOut)) {
                if (myCurrentView != null) {
                    localObject1 = (SocketOut) jGoObject;
                    localObject2 = myCurrentView;
                    if (localObject2 != null) {
                        new SocketOutDialog(((GCView) localObject2).getFrame(), (SocketOut) localObject1, (GCView) localObject2).setVisible(true);
                    }
                }
                this.selectedObject = null;
                AppAction.updateAllActions();
            }
            if (((jGoObject instanceof XMLMessageIn)) && (myCurrentView != null)) {
                this.contextXMLMessageIn.show(myCurrentView, coord.x, coord.y);
            }
            if (((jGoObject instanceof XMLMessageOut)) && (myCurrentView != null)) {
                this.contextXMLMessageOut.show(myCurrentView, coord.x, coord.y);
            }
            if ((jGoObject instanceof Browser)) {
                if (myCurrentView != null) {
                    this.contextBrowser.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
                }
            } else if (((jGoObject instanceof JGoDrawable)) && (!(jGoObject instanceof GCLink)) && (myCurrentView != null)) {
                this.contextDrawable.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if (((jGoObject instanceof Icon)) && (myCurrentView != null)) {
                this.contextIcon.show(myCurrentView, (int) coord.getX(), (int) coord.getY());
            }
            if ((jGoObject instanceof ShowWorkspaceButton)) {
                if (myCurrentView != null) {
                    new ShowWorkspaceDialog(myCurrentView.getFrame(), myCurrentView.getDoc(), (ShowWorkspaceButton) jGoObject, myCurrentView).setVisible(true);
                }
                AppAction.updateAllActions();
            }
        }
    }

    public void workspaceBodyAction() {
        WorkspaceObject localWorkspaceObject = (WorkspaceObject) this.selectedObject;
        handleWorkspaceObject(localWorkspaceObject);
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    public void inspectAction() {
        if ((this.selectedObject instanceof Hierarchical)) {
            Hierarchical localObject = (Hierarchical) this.selectedObject;
            Inspector.inspect(localObject, myCurrentView);
        } else if ((this.selectedObject instanceof GrafcetProcedure)) {
            GrafcetProcedure localObject = (GrafcetProcedure) this.selectedObject;
            Inspector.inspect(localObject, myCurrentView);
        } else if ((this.selectedObject instanceof LabCommObject)) {
            LabCommObject localObject = (LabCommObject) this.selectedObject;
            Inspector.inspect(localObject, myCurrentView);
        }
        Object localObject = Inspector.getInspector();
        if ((localObject != null) && (!((JDialog) localObject).isVisible())) {
            ((JDialog) localObject).setVisible(true);
        }
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    public void closeConnectionAction() {
        if ((this.selectedObject instanceof ByteStreamIn)) {
            ByteStreamIn localByteStreamIn = (ByteStreamIn) this.selectedObject;
            localByteStreamIn.shutdownConnection();
            AppAction.updateAllActions();
        }
        this.selectedObject = null;
    }

    public void closeConnectionOutAction() {
        if ((this.selectedObject instanceof ByteStreamOut)) {
            ByteStreamOut localByteStreamOut = (ByteStreamOut) this.selectedObject;
            localByteStreamOut.shutdownConnection();
            AppAction.updateAllActions();
        }
        this.selectedObject = null;
    }

    public void openConnectionAction() {
        if ((this.selectedObject instanceof ByteStreamIn)) {
            ByteStreamIn localByteStreamIn = (ByteStreamIn) this.selectedObject;
            localByteStreamIn.connect();
            AppAction.updateAllActions();
        }
        this.selectedObject = null;
    }

    public void openConnectionOutAction() {
        if ((this.selectedObject instanceof ByteStreamOut)) {
            ByteStreamOut localByteStreamOut = (ByteStreamOut) this.selectedObject;
            localByteStreamOut.connect();
            AppAction.updateAllActions();
        }
        this.selectedObject = null;
    }

    void byteStreamInEditAction() {
        ByteStreamIn localByteStreamIn = (ByteStreamIn) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new ByteStreamInDialog(localGCView.getFrame(), localGCView.getDoc(), localByteStreamIn, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void byteStreamOutEditAction() {
        ByteStreamOut localByteStreamOut = (ByteStreamOut) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new ByteStreamOutDialog(localGCView.getFrame(), localGCView.getDoc(), localByteStreamOut, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    public void workspaceEditAction() {
        WorkspaceObject localWorkspaceObject = (WorkspaceObject) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new WorkspaceDialog(localGCView.getFrame(), localGCView.getDoc(), localWorkspaceObject, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    public void labCommObjectEditAction() {
        new LabCommObjectDialog(myCurrentView.getFrame(), (LabCommObject) this.selectedObject, myCurrentView).setVisible(true);
        this.selectedObject = null;
        updateActions();
    }

    public void xmlMessageInBodyAction() {
        XMLMessageIn localXMLMessageIn = (XMLMessageIn) this.selectedObject;
        handleXMLMessageIn(localXMLMessageIn);
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    public void xmlMessageOutBodyAction() {
        XMLMessageOut localXMLMessageOut = (XMLMessageOut) this.selectedObject;
        handleXMLMessageOut(localXMLMessageOut);
        this.selectedObject = null;
        AppAction.updateAllActions();
    }

    public void xmlMessageInEditAction() {
        XMLMessageIn localXMLMessageIn = (XMLMessageIn) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new XMLMessageInDialog(localGCView.getFrame(), localGCView.getDoc(), localXMLMessageIn, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    public void xmlMessageInProcedureAction() {
        XMLMessageIn localXMLMessageIn = (XMLMessageIn) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new XMLMessageInProcedureDialog(localGCView.getFrame(), localGCView.getDoc(), localXMLMessageIn, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    public void xmlMessageOutEditAction() {
        XMLMessageOut localXMLMessageOut = (XMLMessageOut) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new XMLMessageOutDialog(localGCView.getFrame(), localGCView.getDoc(), localXMLMessageOut, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    public void handleWorkspaceObject(WorkspaceObject paramWorkspaceObject) {
        if (!paramWorkspaceObject.hasFrame()) {
            paramWorkspaceObject.showWorkspace();
        } else if (!paramWorkspaceObject.isShowing()) {
            paramWorkspaceObject.showWorkspaceLite();
        } else {
            paramWorkspaceObject.hideWorkspace();
        }
    }

    public void handleXMLMessageIn(XMLMessageIn paramXMLMessageIn) {
        if (!paramXMLMessageIn.hasFrame()) {
            paramXMLMessageIn.showWorkspace();
        } else if (!paramXMLMessageIn.isShowing()) {
            paramXMLMessageIn.showWorkspaceLite();
        } else {
            paramXMLMessageIn.hideWorkspace();
        }
    }

    public void handleXMLMessageOut(XMLMessageOut paramXMLMessageOut) {
        if (!paramXMLMessageOut.hasFrame()) {
            paramXMLMessageOut.showWorkspace();
        } else if (!paramXMLMessageOut.isShowing()) {
            paramXMLMessageOut.showWorkspaceLite();
        } else {
            paramXMLMessageOut.hideWorkspace();
        }
    }

    void macroStepStubsAction() {
        MacroStep localMacroStep = (MacroStep) this.selectedObject;
        localMacroStep.updateStubs();
    }

    void macroStepEditAction() {
        MacroStep localMacroStep = (MacroStep) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new MacroStepDialog(localGCView.getFrame(), localMacroStep, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void macroStepSetNameAction() {
        MacroStep localMacroStep = (MacroStep) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new StepNameDialog(localGCView.getFrame(), localMacroStep, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void macroStepBodyAction() {
        final MacroStep localMacroStep = (MacroStep) this.selectedObject;
        if (localMacroStep.frame == null) {
            localMacroStep.myContentDocument.setName(localMacroStep.myName.getText());
            MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localMacroStep.myName.getText(), true);
            localMyJInternalFrame.setDefaultCloseOperation(2);
            final GCView localGCView = new GCView(localMacroStep.myContentDocument);
            localMacroStep.myContentDocument.setView(localGCView);
            localGCView.setStepCounter(localMacroStep.stepCounterInt);
            localGCView.initialize(this, localMyJInternalFrame);
            localGCView.layer = (myCurrentView.layer + 1);
            localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentObject = localMacroStep;
                    Editor.myCurrentView = localGCView;
                    Editor.topLevelView = false;
                    Editor.compilationView = true;
                    localGCView.requestFocus();
                    AppAction.updateAllActions();
                }

                public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentObject = null;
                    localMacroStep.rgbColor = localMacroStep.myContentDocument.getPaperColor().getRGB();
                    localMacroStep.bounds = localMacroStep.frame.getBounds();
                    localMacroStep.point = localMacroStep.view.getViewPosition();
                    localMacroStep.myContentDocument.currentScale = localMacroStep.view.getScale();
                    localMacroStep.stepCounterInt = localMacroStep.view.getStepCounter();
                    localMacroStep.frame = null;
                    localMacroStep.view = null;
                    localMacroStep.setDraggable(true);
                    Editor.myCurrentView = localMacroStep.parentView;
                    AppAction.updateAllActions();
                }

                public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }
            });
            localMacroStep.parentView = myCurrentView;
            localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
            localMyJInternalFrame.getContentPane().add(localGCView);
            getDesktop().add(localMyJInternalFrame, new Integer(localGCView.layer));
            if (localMacroStep.bounds == null) {
                localMyJInternalFrame.setSize(400, 400);
                Point localPoint = localMacroStep.getLocation();
                myCurrentView.convertDocToView(localPoint);
                localMyJInternalFrame.setLocation((int) localPoint.getX() + 10, (int) localPoint.getY() + 40);
            } else {
                localMacroStep.myContentDocument.setPaperColor(new Color(localMacroStep.rgbColor));
                localGCView.setScale(localMacroStep.myContentDocument.currentScale);
                if (localMacroStep.point != null) {
                    localGCView.setViewPosition(localMacroStep.point);
                }
                localMyJInternalFrame.setBounds(localMacroStep.bounds);
            }
            localMyJInternalFrame.show();
            localGCView.initializeDragDropHandling();
            myCurrentView = localGCView;
            topLevelView = false;
            localGCView.requestFocus();
            localMacroStep.frame = localMyJInternalFrame;
            localMacroStep.view = localGCView;
            localMacroStep.view.setDragDropEnabled(!localMacroStep.myContentDocument.executing);
            localMacroStep.myContentDocument.setModificationProperty(localMacroStep.myContentDocument.modificationProperty);
            AppAction.updateAllActions();
        } else if (!localMacroStep.frame.isShowing()) {
            if (localMacroStep.frame.isIcon()) {
                try {
                    localMacroStep.frame.setIcon(false);
                } catch (Exception localException1) {
                }
            }
            localMacroStep.frame.show();
            localMacroStep.view.requestFocus();
        } else {
            localMacroStep.rgbColor = localMacroStep.myContentDocument.getPaperColor().getRGB();
            localMacroStep.bounds = localMacroStep.frame.getBounds();
            localMacroStep.myContentDocument.currentScale = localMacroStep.view.getScale();
            localMacroStep.point = localMacroStep.view.getViewPosition();
            localMacroStep.stepCounterInt = localMacroStep.view.getStepCounter();
            try {
                localMacroStep.frame.setClosed(true);
            } catch (Exception localException2) {
            }
            localMacroStep.setDraggable(true);
            localMacroStep.frame = null;
            localMacroStep.view = null;
            myCurrentView = localMacroStep.parentView;
            myCurrentView.requestFocus();
            AppAction.updateAllActions();
        }
    }

    public void stepFusionSetBodyAction() {
        final StepFusionSet localStepFusionSet = (StepFusionSet) this.selectedObject;
        if (localStepFusionSet.frame == null) {
            localStepFusionSet.myContentDocument.setName(localStepFusionSet.myName.getText());
            MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localStepFusionSet.myName.getText(), true);
            localMyJInternalFrame.setDefaultCloseOperation(2);
            final GCView localGCView = new GCView(localStepFusionSet.myContentDocument);
            localGCView.setStepCounter(localStepFusionSet.stepCounterInt);
            localGCView.initialize(this, localMyJInternalFrame);
            localGCView.layer = (myCurrentView.layer + 1);
            localStepFusionSet.myContentDocument.setPaperColor(new Color(255, 255, 255));
            localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentObject = localStepFusionSet;
                    Editor.myCurrentView = localGCView;
                    Editor.topLevelView = false;
                    Editor.compilationView = true;
                    localGCView.requestFocus();
                    AppAction.updateAllActions();
                }

                public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentObject = null;
                    localStepFusionSet.rgbColor = localStepFusionSet.myContentDocument.getPaperColor().getRGB();
                    localStepFusionSet.bounds = localStepFusionSet.frame.getBounds();
                    localStepFusionSet.myContentDocument.currentScale = localStepFusionSet.view.getScale();
                    localStepFusionSet.point = localStepFusionSet.view.getViewPosition();
                    localStepFusionSet.stepCounterInt = localStepFusionSet.view.getStepCounter();
                    Editor.myCurrentView = localStepFusionSet.parentView;
                    localStepFusionSet.frame = null;
                    localStepFusionSet.view = null;
                    AppAction.updateAllActions();
                }

                public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }
            });
            localStepFusionSet.parentView = myCurrentView;
            localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
            localMyJInternalFrame.getContentPane().add(localGCView);
            getDesktop().add(localMyJInternalFrame, new Integer(localGCView.layer));
            if (localStepFusionSet.bounds == null) {
                localMyJInternalFrame.setSize(400, 600);
                Point localPoint = localStepFusionSet.getLocation();
                myCurrentView.convertDocToView(localPoint);
                localMyJInternalFrame.setLocation((int) localPoint.getX() + 10, (int) localPoint.getY() + 40);
            } else {
                localStepFusionSet.myContentDocument.setPaperColor(new Color(localStepFusionSet.rgbColor));
                localGCView.setScale(localStepFusionSet.myContentDocument.currentScale);
                if (localStepFusionSet.point != null) {
                    localGCView.setViewPosition(localStepFusionSet.point);
                }
                localMyJInternalFrame.setBounds(localStepFusionSet.bounds);
            }
            localMyJInternalFrame.show();
            localGCView.initializeDragDropHandling();
            myCurrentView = localGCView;
            localStepFusionSet.frame = localMyJInternalFrame;
            localStepFusionSet.view = localGCView;
            localStepFusionSet.view.setDragDropEnabled(!localStepFusionSet.myContentDocument.executing);
            topLevelView = false;
            localGCView.requestFocus();
            AppAction.updateAllActions();
        } else if (!localStepFusionSet.frame.isShowing()) {
            if (localStepFusionSet.frame.isIcon()) {
                try {
                    localStepFusionSet.frame.setIcon(false);
                } catch (Exception localException1) {
                }
            }
            localStepFusionSet.frame.show();
            localStepFusionSet.view.requestFocus();
        } else {
            localStepFusionSet.rgbColor = localStepFusionSet.myContentDocument.getPaperColor().getRGB();
            localStepFusionSet.bounds = localStepFusionSet.frame.getBounds();
            localStepFusionSet.myContentDocument.currentScale = localStepFusionSet.view.getScale();
            localStepFusionSet.point = localStepFusionSet.view.getViewPosition();
            localStepFusionSet.stepCounterInt = localStepFusionSet.view.getStepCounter();
            try {
                localStepFusionSet.frame.setClosed(true);
            } catch (Exception localException2) {
            }
            localStepFusionSet.frame = null;
            localStepFusionSet.view = null;
            myCurrentView = localStepFusionSet.parentView;
            myCurrentView.requestFocus();
            AppAction.updateAllActions();
        }
    }

    void stepFusionSetEditAction() {
        StepFusionSet localStepFusionSet = (StepFusionSet) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new StepFusionSetDialog(localGCView.getFrame(), localStepFusionSet, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void drawableEditAction() {
        String str = "Drawable Properties";
        DrawablePropsDialog localDrawablePropsDialog = new DrawablePropsDialog(myCurrentView.getFrame(), str, (JGoDrawable) this.selectedObject);
        localDrawablePropsDialog.setVisible(true);
        this.selectedObject = null;
        updateActions();
    }

    void iconEditAction() {
        IconDialog localIconDialog = new IconDialog(myCurrentView.getFrame(), myCurrentView.getDoc(), (Icon) this.selectedObject, myCurrentView);
        localIconDialog.setVisible(true);
        this.selectedObject = null;
        updateActions();
    }

    void textEditAction() {
        TextPropsDialog localTextPropsDialog = new TextPropsDialog(myCurrentView.getFrame(), "", (JGoText) this.selectedObject);
        localTextPropsDialog.setVisible(true);
        this.selectedObject = null;
        updateActions();
    }

    void drawableNameAction() {
        Referencable localReferencable = (Referencable) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new DrawableNameDialog(localGCView.getFrame(), localGCView.getDoc(), localReferencable, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void stepIconAction() {
        IconStep localIconStep = (IconStep) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new StepIconDialog(localGCView.getFrame(), localGCView.getDoc(), localIconStep, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void browserNameAction() {
        Referencable localReferencable = (Referencable) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new BrowserNameDialog(localGCView.getFrame(), localGCView.getDoc(), localReferencable, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void browserEditAction() {
        BrowserDialog localBrowserDialog = new BrowserDialog(myCurrentView.getFrame(), (Browser) this.selectedObject, myCurrentView);
        localBrowserDialog.setVisible(true);
        this.selectedObject = null;
        updateActions();
    }

    void iconNameAction() {
        Referencable localReferencable = (Referencable) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new DrawableNameDialog(localGCView.getFrame(), localGCView.getDoc(), localReferencable, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void plotterEditAction() {
        SFCPlotter localSFCPlotter = (SFCPlotter) this.selectedObject;
        GCView localGCView = myCurrentView;
        if (localGCView != null) {
            new SFCPlotterDialog(localGCView.getFrame(), localGCView.getDoc(), localSFCPlotter, localGCView).setVisible(true);
        }
        this.selectedObject = null;
        updateActions();
    }

    void plotterShowAction() {
        final SFCPlotter localSFCPlotter = (SFCPlotter) this.selectedObject;
        if (localSFCPlotter.plotter != null) {
            if (localSFCPlotter.frame == null) {
                MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localSFCPlotter.myName.getText(), true);
                localMyJInternalFrame.setDefaultCloseOperation(2);
                localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                        Editor.myCurrentView = null;
                        Editor.myCurrentObject = localSFCPlotter;
                        Editor.topLevelView = false;
                        Editor.compilationView = false;
                        AppAction.updateAllActions();
                    }

                    public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }

                    public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }

                    public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }

                    public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                        Editor.myCurrentObject = null;
                        Editor.myCurrentView = localSFCPlotter.parentView;
                        Editor.myCurrentView.requestFocus();
                        localSFCPlotter.bounds = localSFCPlotter.frame.getBounds();
                        localSFCPlotter.frame = null;
                        AppAction.updateAllActions();
                    }

                    public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }

                    public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }
                });
                localSFCPlotter.parentView = myCurrentView;
                Container localContainer = localMyJInternalFrame.getContentPane();
                localContainer.setLayout(new MyBorderLayout());
                localContainer.add(localSFCPlotter.plotter, "Center");
                JPanel localJPanel = new JPanel();
                final MyJButton localMyJButton1 = new MyJButton("Pause", 80);
                final MyJButton localMyJButton2 = new MyJButton("Continue", 67);
                localMyJButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
                        localSFCPlotter.pausing = true;
                        localMyJButton2.setEnabled(true);
                        localMyJButton1.setEnabled(false);
                    }
                });
                localMyJButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
                        localSFCPlotter.pausing = false;
                        localMyJButton1.setEnabled(true);
                        localMyJButton2.setEnabled(false);
                    }
                });
                localJPanel.add(localMyJButton1);
                localMyJButton1.setEnabled(!localSFCPlotter.pausing);
                localJPanel.add(localMyJButton2);
                localMyJButton2.setEnabled(localSFCPlotter.pausing);
                localContainer.add(localJPanel, "South");
                if (localSFCPlotter.bounds == null) {
                    localMyJInternalFrame.setSize(300, 200);
                    Point localPoint = localSFCPlotter.getLocation();
                    myCurrentView.convertDocToView(localPoint);
                    localMyJInternalFrame.setLocation((int) localPoint.getX() + 10, (int) localPoint.getY() + 40);
                } else {
                    localMyJInternalFrame.setBounds(localSFCPlotter.bounds);
                }
                getDesktop().add(localMyJInternalFrame, new Integer(myCurrentView.layer + 1));
                localMyJInternalFrame.setClosable(true);
                localMyJInternalFrame.show();
                localSFCPlotter.frame = localMyJInternalFrame;
                AppAction.updateAllActions();
            } else if (!localSFCPlotter.frame.isShowing()) {
                if (localSFCPlotter.frame.isIcon()) {
                    try {
                        localSFCPlotter.frame.setIcon(false);
                    } catch (Exception localException1) {
                    }
                }
                localSFCPlotter.frame.show();
            } else {
                localSFCPlotter.bounds = localSFCPlotter.frame.getBounds();
                try {
                    localSFCPlotter.frame.setClosed(true);
                } catch (Exception localException2) {
                }
                localSFCPlotter.frame = null;
                if (localSFCPlotter.parentView != null) {
                    myCurrentView = localSFCPlotter.parentView;
                    myCurrentView.requestFocus();
                    AppAction.updateAllActions();
                }
            }
        }
    }

    public void handleProcedureStep(final ProcedureStep paramProcedureStep) {
        if (paramProcedureStep.myContentDocument == null) {
            Utils.writeError("No active call.");
        } else if ((paramProcedureStep.frame == null) && (paramProcedureStep.procedure != null)) {
            paramProcedureStep.myContentDocument.setName(paramProcedureStep.procedure.getName());
            MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(paramProcedureStep.procedure.getName(), true);
            localMyJInternalFrame.setDefaultCloseOperation(2);
            final GCView localGCView = new GCView(paramProcedureStep.myContentDocument);
            localGCView.setStepCounter(paramProcedureStep.stepCounterInt);
            localGCView.layer = (myCurrentView.layer + 1);
            localGCView.initialize(this, localMyJInternalFrame, true);
            paramProcedureStep.myContentDocument.frame = localMyJInternalFrame;
            localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = localGCView;
                    Editor.topLevelView = false;
                    localGCView.requestFocus();
                    AppAction.updateAllActions();
                }

                public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = null;
                    Editor.myCurrentView = paramProcedureStep.parentView;
                    paramProcedureStep.view = null;
                    paramProcedureStep.frame = null;
                    AppAction.updateAllActions();
                }

                public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }
            });
            paramProcedureStep.parentView = myCurrentView;
            localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
            localMyJInternalFrame.getContentPane().add(localGCView);
            getDesktop().add(localMyJInternalFrame, new Integer(localGCView.layer));
            if (paramProcedureStep.procedure.bounds == null) {
                localMyJInternalFrame.setSize(400, 400);
                Point localPoint = paramProcedureStep.getLocation();
                myCurrentView.convertDocToView(localPoint);
                localMyJInternalFrame.setLocation((int) localPoint.getX() + 10, (int) localPoint.getY() + 40);
            } else {
                paramProcedureStep.myContentDocument.setPaperColor(new Color(paramProcedureStep.procedure.rgbColor));
                localGCView.setScale(paramProcedureStep.procedure.myContentDocument.currentScale);
                if (paramProcedureStep.procedure.point != null) {
                    localGCView.setViewPosition(paramProcedureStep.procedure.point);
                }
                localMyJInternalFrame.setBounds(paramProcedureStep.procedure.bounds);
            }
            localMyJInternalFrame.setFrameIcon(GCDocument.runIcon);
            localMyJInternalFrame.show();
            localGCView.initializeDragDropHandling();
            myCurrentView = localGCView;
            paramProcedureStep.frame = localMyJInternalFrame;
            paramProcedureStep.view = localGCView;
            paramProcedureStep.view.setDragDropEnabled(!paramProcedureStep.myContentDocument.executing);
            topLevelView = false;
            localGCView.requestFocus();
            AppAction.updateAllActions();
            paramProcedureStep.myContentDocument.setModificationProperty(paramProcedureStep.myContentDocument.modificationProperty);
        } else {
            if (paramProcedureStep.view != null) {
                paramProcedureStep.stepCounterInt = paramProcedureStep.view.getStepCounter();
            }
            try {
                if (paramProcedureStep.frame != null) {
                    paramProcedureStep.frame.setClosed(true);
                }
            } catch (Exception localException) {
                Utils.writeException(localException);
            }
            paramProcedureStep.frame = null;
            paramProcedureStep.view = null;
            if (paramProcedureStep.parentView != null) {
                myCurrentView = paramProcedureStep.parentView;
            }
            if (myCurrentView != null) {
                myCurrentView.requestFocus();
            }
            AppAction.updateAllActions();
        }
    }

    public void processDocChange(JGoDocumentEvent paramJGoDocumentEvent) {
        switch (paramJGoDocumentEvent.getHint()) {
            case 3:
                JGoText localJGoText;
                Object localObject;
                if (((paramJGoDocumentEvent.getJGoObject() instanceof JGoText)) && (paramJGoDocumentEvent.getJGoObject().getParent() != null) && (paramJGoDocumentEvent.getFlags() == 501) && ((paramJGoDocumentEvent.getJGoObject().getParent() instanceof GenericTransition))) {
                    localJGoText = (JGoText) paramJGoDocumentEvent.getJGoObject();
                    localObject = (GenericTransition) localJGoText.getParent();
                    String str1 = localJGoText.getText();
                    if ((str1.equals("1")) || (str1.equals("0"))) {
                        eraseWarnings();
                        StringReader localStringReader = new StringReader(str1);
                        TransitionParser localTransitionParser = new TransitionParser(localStringReader);
                        try {
                            localJGoText.setTextColor(black);
                            ((GenericTransition) localObject).node = null;
                            ((GenericTransition) localObject).node = localTransitionParser.Start();
                        } catch (Throwable localThrowable) {
                            Utils.writeException(localThrowable);
                            giveWarning("Parsing problem for transition in " + ((GenericTransition) localObject).getDocument().getFullName() + ".");
                            localJGoText.setTextColor(red);
                        }
                    }
                }
                if (((paramJGoDocumentEvent.getJGoObject() instanceof JGoText)) && (paramJGoDocumentEvent.getJGoObject().getParent() != null) && (paramJGoDocumentEvent.getFlags() == 501) && ((paramJGoDocumentEvent.getJGoObject().getParent() instanceof AnalogIn))) {
                    localJGoText = (JGoText) paramJGoDocumentEvent.getJGoObject();
                    localObject = (AnalogIn) localJGoText.getParent();
                    if (localJGoText == ((AnalogIn) localObject).myValue) {
                        try {
                            ((AnalogIn) localObject).val = Utils.parseDoubleStrict(localJGoText.getText());
                            localJGoText.setTextColor(Color.BLACK);
                            Inspector.refresh();
                        } catch (NumberFormatException localNumberFormatException1) {
                            localJGoText.setTextColor(Color.RED);
                        }
                    }
                }
                String str2;
                if (((paramJGoDocumentEvent.getJGoObject() instanceof JGoText)) && (paramJGoDocumentEvent.getJGoObject().getParent() != null) && (paramJGoDocumentEvent.getFlags() == 501) && ((paramJGoDocumentEvent.getJGoObject().getParent() instanceof BooleanVariable))) {
                    localJGoText = (JGoText) paramJGoDocumentEvent.getJGoObject();
                    localObject = (BooleanVariable) localJGoText.getParent();
                    str2 = localJGoText.getText();
                    if (localJGoText == ((BooleanVariable) localObject).myValue) {
                        if ((str2.compareTo("0") == 0) && (((BooleanVariable) localObject).val)) {
                            ((BooleanVariable) localObject).setStoredBoolAction(false);
                        } else if ((str2.compareTo("1") == 0) && (!((BooleanVariable) localObject).val)) {
                            ((BooleanVariable) localObject).setStoredBoolAction(true);
                        }
                    }
                }
                if (((paramJGoDocumentEvent.getJGoObject() instanceof JGoText)) && (paramJGoDocumentEvent.getJGoObject().getParent() != null) && (paramJGoDocumentEvent.getFlags() == 501) && ((paramJGoDocumentEvent.getJGoObject().getParent() instanceof IntegerVariable))) {
                    localJGoText = (JGoText) paramJGoDocumentEvent.getJGoObject();
                    localObject = (IntegerVariable) localJGoText.getParent();
                    str2 = localJGoText.getText();
                    if (localJGoText == ((IntegerVariable) localObject).myValue) {
                        try {
                            ((IntegerVariable) localObject).setStoredIntAction(Integer.parseInt(str2));
                        } catch (NumberFormatException localNumberFormatException2) {
                            localJGoText.setTextColor(Color.RED);
                        }
                    }
                }
                if (((paramJGoDocumentEvent.getJGoObject() instanceof JGoText)) && (paramJGoDocumentEvent.getJGoObject().getParent() != null) && (paramJGoDocumentEvent.getFlags() == 501) && ((paramJGoDocumentEvent.getJGoObject().getParent() instanceof RealVariable))) {
                    localJGoText = (JGoText) paramJGoDocumentEvent.getJGoObject();
                    localObject = (RealVariable) localJGoText.getParent();
                    str2 = localJGoText.getText();
                    if (localJGoText == ((RealVariable) localObject).myValue) {
                        try {
                            ((RealVariable) localObject).setStoredRealAction(Utils.parseDoubleStrict(str2));
                        } catch (NumberFormatException localNumberFormatException3) {
                            localJGoText.setTextColor(Color.RED);
                        }
                    }
                }
                if (((paramJGoDocumentEvent.getJGoObject() instanceof JGoText)) && (paramJGoDocumentEvent.getJGoObject().getParent() != null) && (paramJGoDocumentEvent.getFlags() == 501) && ((paramJGoDocumentEvent.getJGoObject().getParent() instanceof StringVariable))) {
                    localJGoText = (JGoText) paramJGoDocumentEvent.getJGoObject();
                    localObject = (StringVariable) localJGoText.getParent();
                    str2 = localJGoText.getText();
                    if (localJGoText == ((StringVariable) localObject).myValue) {
                        ((StringVariable) localObject).setStoredStringAction(str2);
                    }
                }
                break;
        }
    }

    public static void giveWarnings(SortedList<CompilationMessage> paramSortedList) {
        Iterator localIterator = paramSortedList.iterator();
        while (localIterator.hasNext()) {
            CompilationMessage localCompilationMessage = (CompilationMessage) localIterator.next();
            if (localCompilationMessage.isError()) {
                giveWarning(localCompilationMessage.getMessage());
            } else {
                giveLightWarning(localCompilationMessage.getMessage());
            }
        }
    }

    public static MyJDesktopPane getDesktop() {
        return myDesktop;
    }

    public void setCurrentView(GCView paramGCView) {
        myCurrentView = paramGCView;
        myCurrentView.requestFocus();
        try {
            myCurrentView.getInternalFrame().setSelected(true);
        } catch (PropertyVetoException localPropertyVetoException) {
            Utils.writeException(localPropertyVetoException, "Setting focus failed.");
        }
        AppAction.updateAllActions();
    }

    public void enableSlideAction() {
        if (!objectHelp("Editor_MiscMenu_EnableSlideMode")) {
            slideMode = true;
        }
    }

    public void disableSlideAction() {
        if (!objectHelp("Editor_MiscMenu_DisableSlideMode")) {
            slideMode = false;
        }
    }

    public static String getLoginName() {
        return loginName;
    }

    public Services getDPWSServices() {
        if (this.dpwsServices == null) {
            try {
                this.dpwsServices = new Services(this.dpwsDiscoveryInterface);
            } catch (Exception localException) {
                Utils.writeException(localException, "DPWS setup failed. Using any DPWS related features will cause problems.");
            }
            if ((this.dpwsServices != null) && (!isTest)) {
                this.dpwsServices.refresh(false);
            }
        }
        return this.dpwsServices;
    }

    public static void subscribe(String paramString) {
        if ((connectedToBlasterServer) && (!paramString.equals(""))) {
            int i = ccom.Subscribe(paramString);
            if (i != 0) {
                writeMessage("Subscription to " + paramString + "failed");
            }
        }
    }

    public static void unSubscribe(String paramString) {
        if ((connectedToBlasterServer) && (!paramString.equals(""))) {
            int i = ccom.UnSubscribe(paramString);
            if (i != 0) {
                writeMessage("Unsubscription to " + paramString + "failed");
            }
        }
    }

    public static void initBlasterAction() {
        if (!connectedToBlasterServer) {
            ccom = new CCOM(singleton);
            int i = ccom.Connect(loginName, serverIP, port);
            if (i == 0) {
                connectedToBlasterServer = true;
            } else {
                writeMessage("CCOM connect failed");
            }
        }
        updateActions();
    }

    public void blasterServerAction() {
        new ServerDialog(this).setVisible(true);
    }

    public void dpwsServicesAction() {
        try {
            this.dpwsServicesDialog = new ServicesDialog(this, getDPWSServices());
            this.dpwsServicesDialog.setVisible(true);
        } catch (Exception localException) {
            Utils.writeException(localException);
        }
        AppAction.updateAllActions();
    }

    public void dpwsServiceDocumentation(DPWSPortType paramDPWSPortType) {
        if (this.dpwsServicesDialog == null) {
            dpwsServicesAction();
        }
        this.dpwsServicesDialog.toFront();
        this.dpwsServicesDialog.setSelection(paramDPWSPortType);
    }

    public void dpwsServicesClosed() {
        this.dpwsServicesDialog = null;
        AppAction.updateAllActions();
    }

    public void aboutAction() {
        if (!objectHelp("Editor_HelpMenu_About")) {
            new AboutDialog(this).setVisible(true);
        }
    }

    public void ReceiveMessage(String paramString1, String paramString2, String paramString3) {
        Utils.writeDebug("ReceiveMessage called: \"" + paramString1 + "\"");
        try {
            int i = paramString1.indexOf("<CCOMBody>");
            String str1 = paramString1.substring(0, i);
            String str2 = paramString1.substring(i + 10);
            i = str2.indexOf("</CCOMBody>");
            String str3 = str2.substring(i);
            str2 = str2.substring(0, i);
            int j = str2.indexOf("<");
            while (j != -1) {
                str2 = str2.substring(j);
                int k = str2.indexOf(">");
                int m = str2.indexOf(" ");
                String str4 = null;
                if (m != -1) {
                    str4 = str2.substring(1, Math.min(k, m));
                } else {
                    str4 = str2.substring(1, k);
                }
                int n = str2.indexOf("</" + str4 + ">");
                String str5 = str2.substring(0, n + str4.length() + 3);
                str2 = str2.substring(n + str4.length() + 3);
                j = str2.indexOf("<");
                Document localDocument = XMLProtocol.parseXML(str5);
                Element localElement = localDocument.getDocumentElement();
                String str6 = localElement.getTagName();
                if (str6.equals("Process_variable")) {
                    String str7 = XMLProtocol.getTag(localDocument);
                    String str8 = XMLProtocol.getValue(localDocument);
                    XMLIn.setXMLIn(str7, str8);
                } else {
                    XMLIn.setXMLIn(str6, str1 + "<CCOMBody>" + str5 + str3);
                }
            }
        } catch (Exception localException) {
            Utils.writeException(localException);
        }
    }

    public static void mockUpdate() {
        mockUpdate("<ChemMessageContent> <SUBJECT>mySubject</SUBJECT> <CCOMHeader Type='myType' Origin='myOrigin' MustReply='myMustReply' Handle='1234'/> <CCOMBody><Nothingness/></CCOMBody> </ChemMessageContent>");
    }

    public static void mockUpdate(String paramString) {
        Document localDocument = XMLProtocol.parseXML(paramString);
        Element localElement = localDocument.getDocumentElement();
        String str = localElement.getTagName();
        XMLIn.setXMLIn(str, paramString);
    }

    public static void publishMessage(String paramString1, String paramString2) {
        if (!connectedToBlasterServer) {
            writeMessage("PublishMessage problem: Not connected to CCOM.");
            return;
        }
        String str = "<CCOMEnvelope><CCOMHeader Type=\"Data\" Origin=\"" + loginName + "\" MustReply=\"No\"></CCOMHeader><CCOMBody>" + paramString2 + "</CCOMBody></CCOMEnvelope>";
        int i = ccom.PublishMessage(str, paramString1, false);
        if (i != 0) {
            writeMessage("PublishMessage problem: Status = " + i);
        }
    }

    public static void publishMessage(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5) {
        if (!connectedToBlasterServer) {
            writeMessage("PublishMessage problem: Not connected to CCOM.");
            return;
        }
        String str = "";
        if (paramInt != 0) {
            str = "<CCOMEnvelope><CCOMHeader Handle= \"" + paramInt + "\" Type=\"" + paramString5 + "\" Origin=\"" + paramString3 + "\" MustReply=\"" + paramString4 + "\"></CCOMHeader><CCOMBody>" + paramString2 + "</CCOMBody></CCOMEnvelope>";
        } else {
            str = "<CCOMEnvelope><CCOMHeader Type=\"" + paramString5 + "\" Origin=\"" + paramString3 + "\" MustReply=\"" + paramString4 + "\"></CCOMHeader><CCOMBody>" + paramString2 + "</CCOMBody></CCOMEnvelope>";
        }
        int i = 0;
        try {
            i = ccom.PublishMessage(str, paramString1, false);
        } catch (Exception localException) {
            Utils.writeException(localException);
        }
        if (i != 0) {
            writeMessage("PublishMessage problem: Status = " + i);
        }
    }

    public static int sendMessage(String paramString1, String paramString2) {
        if (!connectedToBlasterServer) {
            writeMessage("SendMessage problem: Not connected to CCOM.");
            return 0;
        }
        String str = "<CCOMEnvelope><CCOMHeader Type=\"Data\" Origin=\"" + loginName + "\" MustReply=\"no\"></CCOMHeader><CCOMBody>" + paramString2 + "</CCOMBody></CCOMEnvelope>";
        String[] arrayOfString = new String[1];
        arrayOfString[0] = paramString1;
        double d = ccom.SendMessage(arrayOfString, str, 0, false);
        int i = (int) Math.floor(d);
        int j = (int) (10.0D * (d - i));
        if (j != 0) {
            writeMessage("SendMessage problem: Status = " + j);
        }
        return i;
    }

    public static int sendMessage(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5) {
        if (!connectedToBlasterServer) {
            writeMessage("SendMessage problem: Not connected to CCOM.");
            return 0;
        }
        String str = "";
        if (paramInt != 0) {
            str = "<CCOMEnvelope><CCOMHeader Handle=\"" + paramInt + "\" Type=\"" + paramString5 + "\" Origin=\"" + paramString3 + "\" MustReply=\"" + paramString4 + "\"></CCOMHeader><CCOMBody>" + paramString2 + "</CCOMBody></CCOMEnvelope>";
            int i = ccom.ReplySendMessage(paramString1, str, false);
            if (i != 0) {
                writeMessage("ReplySendMessage problem: Status = " + i);
            }
            return i;
        }
        str = "<CCOMEnvelope><CCOMHeader Type=\"" + paramString5 + "\" Origin=\"" + paramString3 + "\" MustReply=\"" + paramString4 + "\"></CCOMHeader><CCOMBody>" + paramString2 + "</CCOMBody></CCOMEnvelope>";
        Utils.writeDebug("\n\nSendMessage message = " + str);
        String[] arrayOfString = new String[1];
        arrayOfString[0] = paramString1;
        double d = ccom.SendMessage(arrayOfString, str, 0, false);
        int j = (int) Math.floor(d);
        int k = (int) (10.0D * (d - j));
        if (k != 0) {
            writeMessage("SendMessage problem: Status = " + k);
        }
        return j;
    }

    public static void logoutBlasterAction() {
        if (connectedToBlasterServer) {
            int i = ccom.Disconnect();
            if (i == 0) {
                connectedToBlasterServer = false;
            } else {
                writeMessage("CCOM disconnect failed");
            }
        }
        updateActions();
    }

    private static Object makeObj(String paramString) {
        return new Object() {
            public String toString() {
                //return this.val$item;
                return paramString;
            }
        };
    }

    /**
     * Ajoute un message dans la fenètre messages
     * @param msg Message à afficher
     */
    public static void writeMessage(String msg) {
        messageBox.insertItemAt(makeObj(msg), 0);
        messageBox.setSelectedIndex(0);
    }

    /**
     * Supprime tous les messages
     */
    public static void clearMessages() {
        messageBox.removeAllItems();
        messageBoxIndex = 0;
    }

    public static void giveWarning(String paramString) {
        if (generateWarnings) {
            if (myCurrentView != null) {
                myCurrentView.getDoc().compiledOK = false;
            }
            errorCount += 1;
            messageBox.insertItemAt(makeObj("Error: " + paramString), messageBoxIndex);
            messageBoxIndex += 1;
        }
        if (printWarnings) {
            System.out.println("Error: " + paramString);
        }
    }

    public static void giveLightWarning(String paramString) {
        if (generateWarnings) {
            warningCount += 1;
            messageBox.insertItemAt(makeObj("Warning: " + paramString), messageBoxIndex);
            messageBoxIndex += 1;
        }
        if (printWarnings) {
            System.out.println("Warning: " + paramString);
        }
    }

    public static void giveInformation(String paramString) {
        if (generateWarnings) {
            messageBox.insertItemAt(makeObj("Information: " + paramString), messageBoxIndex);
            messageBoxIndex += 1;
        }
        if (printWarnings) {
            System.out.println("Information: " + paramString);
        }
    }

    /**
     * Résumé de la compilation fournir les erreurs et warnings
     */
    public void summarize() {
        messageBox.insertItemAt(makeObj("" + errorCount + " errors, " + warningCount + " warnings"), 0);
        messageBox.setSelectedIndex(0);
    }

    /**
     * Efface toutes les notif de message box et réinitialise les compteurs d'erreurs 
     * et de warning
     */
    public static void eraseWarnings() {
        if (generateWarnings) {
            myCurrentView.getDoc().compiledOK = true;
            messageBox.removeAllItems();
            messageBoxIndex = 0;
            warningCount = 0;
            errorCount = 0;
        }
    }

    public static Object findObject(String paramString) {
        Object localObject1 = null;
        int i = 0;
        String str = paramString;
        Object localObject2 = null;
        ArrayList localArrayList = topGrafcharts.getStorage();
        Object localObject4;
        for (int j = str.indexOf('.'); j != -1; j = str.indexOf('.')) {
            String localObject3 = str.substring(0, j);
            str = str.substring(j + 1);
            i = 0;
            localObject4 = localArrayList.iterator();
            while ((i == 0) && (((Iterator) localObject4).hasNext())) {
                localObject1 = ((Iterator) localObject4).next();
                if ((localObject1 instanceof Referencable)) {
                    Referencable localReferencable = (Referencable) localObject1;
                    if (localReferencable.getName().compareTo((String) localObject3) == 0) {
                        i = 1;
                        if ((localReferencable instanceof SymbolTableObject)) {
                            localArrayList = ((SymbolTableObject) localReferencable).getSymbolTable();
                        }
                    }
                }
            }
        }
        i = 0;
        Object localObject3 = localArrayList.iterator();
        while ((i == 0) && (((Iterator) localObject3).hasNext())) {
            localObject1 = ((Iterator) localObject3).next();
            if ((localObject1 instanceof Referencable)) {
                localObject4 = (Referencable) localObject1;
                if (((Referencable) localObject4).getName().compareTo(str) == 0) {
                    i = 1;
                    localObject2 = localObject4;
                }
            }
        }
        return localObject2;
    }

    public GCDocument openWorkspace(String paramString) {
        GCDocument localGCDocument = null;
        String str = paramString;
        Document localDocument = loadXMLDocumentFromFile(str);
        if (localDocument != null) {
            localGCDocument = new GCDocument();
            localGCDocument.setSuspendUpdates(true);
            localGCDocument.loadXML(localDocument.getDocumentElement());
            topGrafcharts.add(localGCDocument);
            localGCDocument.setWriteFileLocation(str);
            localGCDocument.setReadFileLocation(str);
            final GCView localGCView = new GCView(localGCDocument);
            localGCDocument.setView(localGCView);
            MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localGCDocument.getName(), false);
            localMyJInternalFrame.setDefaultCloseOperation(2);
            localGCView.initialize(this, localMyJInternalFrame);
            localGCView.setScale(localGCDocument.currentScale);
            if (localGCDocument.point != null) {
                localGCView.setViewPosition(localGCDocument.point);
            }
            localMyJInternalFrame.setBounds(localGCDocument.bounds);
            localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.myCurrentView = localGCView;
                    Editor.compilationView = true;
                    Editor.topLevelView = true;
                    localGCView.requestFocus();
                    AppAction.updateAllActions();
                }

                public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    Editor.this.closeMacros(localGCView.getDoc());
                    Editor.topGrafcharts.remove(localGCView.getDoc());
                    Editor.compilationView = false;
                    Editor.myCurrentView = null;
                    AppAction.updateAllActions();
                }

                public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }

                public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                }
            });
            localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
            localMyJInternalFrame.getContentPane().add(localGCView);
            getDesktop().add(localMyJInternalFrame);
            localMyJInternalFrame.show();
            localGCView.initializeDragDropHandling();
            localGCDocument.setSuspendUpdates(false);
            if (!localGCDocument.modificationProperty) {
                localGCDocument.setModificationProperty(false);
            }
            localGCView.hasHorizontalScrollBar = localGCDocument.horizontalScrollBar;
            if (!localGCDocument.horizontalScrollBar) {
                localGCView.setHorizontalScrollBar(null);
            }
            localGCView.hasVerticalScrollBar = localGCDocument.verticalScrollBar;
            if (!localGCDocument.verticalScrollBar) {
                localGCView.setVerticalScrollBar(null);
            }
        }
        return localGCDocument;
    }

    public void startWorkspace(GCDocument paramGCDocument) {
        if (paramGCDocument.compiledOK) {
            paramGCDocument.initializeDocument(paramGCDocument, paramGCDocument.isSimulating());
            paramGCDocument.getView().setDragDropEnabled(false);
            paramGCDocument.setDragAndDrop(false);
            paramGCDocument.start();
        }
    }

    public void startNamedWorkspace(String paramString) {
        GCDocument localGCDocument = (GCDocument) findObject(paramString);
        startWorkspace(localGCDocument);
    }

    public boolean compileWorkspace(GCDocument paramGCDocument) {
        paramGCDocument.compiledOK = true;
        generateWarnings = true;
        eraseWarnings();
        SymbolTableHelper.warnAboutNameConflicts(paramGCDocument);
        setOutHack(paramGCDocument, paramGCDocument);
        compileDocument(paramGCDocument, false);
        compileDocument(paramGCDocument, false);
        return paramGCDocument.compiledOK;
    }

    public boolean compileNamedWorkspace(String paramString) {
        GCDocument localGCDocument = (GCDocument) findObject(paramString);
        return compileWorkspace(localGCDocument);
    }

    public void stopWorkspace(GCDocument paramGCDocument) {
        if (paramGCDocument.executing) {
            paramGCDocument.stopThread();
            paramGCDocument.stopDocument();
            paramGCDocument.getView().setDragDropEnabled(true);
            paramGCDocument.setDragAndDrop(true);
            paramGCDocument.setSkipsUndoManager(false);
        }
    }

    public void stopNamedWorkspace(String paramString) {
        GCDocument localGCDocument = (GCDocument) findObject(paramString);
        stopWorkspace(localGCDocument);
    }

    public void deleteWorkspace(GCDocument paramGCDocument) {
        try {
            paramGCDocument.myView.getInternalFrame().setClosed(true);
        } catch (PropertyVetoException localPropertyVetoException) {
            Utils.writeException(localPropertyVetoException);
        }
    }

    public void deleteNamedWorkspace(String paramString) {
        GCDocument localGCDocument = (GCDocument) findObject(paramString);
        if (localGCDocument != null) {
            deleteWorkspace(localGCDocument);
        } else {
            System.out.println("Error: No workspace named \"" + paramString + "\" found.");
        }
    }

    public void newObject(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2) {
        Object localObject = findObject(paramString3);
        GCDocument localGCDocument = null;
        if ((localObject instanceof GCDocument)) {
            localGCDocument = (GCDocument) localObject;
        } else if ((localObject instanceof Hierarchical)) {
            localGCDocument = ((Hierarchical) localObject).getContentDocument();
        }
        if (localGCDocument != null) {
            localGCDocument.newObject(paramString1, paramString2, paramInt1, paramInt2);
        }
    }

    private void generateRecentFiles(MyJMenu recentMenu, int mawRecent) { 
        //SAuvegarder les paths des files sera le plus avantageur
        File [] recentFiles = new File[]{
            new File("../examples/feux"),
            new File("/home/dimon/GIT5/Memoire/JGrafchart/TestJG/recup2"),
            new File("/home/dimon/GIT5/Memoire/JGrafchart/TestJG/g7complet1")            
        };
        for (File recentFile : recentFiles) {        
            recentMenu.add(new AppAction(recentFile.getName(), Utils.newImageIcon("buttons/Properties24.gif"), this) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Editor.this.openRealFileAction(recentFile.getAbsolutePath());
                }
                
                @Override
                public boolean canAct() {
                    return true;
                }
            });
            //if 1er localJMenuItem.setAccelerator(keyStroke); 
        }   
    }

    private void openRealFileAction(String path) {
        GCDocument localGCDocument;
        Document xmlDocument = loadXMLDocumentFromFile(path);
        if (xmlDocument != null) {
            localGCDocument = new GCDocument();
            localGCDocument.setSuspendUpdates(true);
            if (localGCDocument.loadXML(xmlDocument.getDocumentElement())) {
                topGrafcharts.add(localGCDocument);
                localGCDocument.setWriteFileLocation(path);
                localGCDocument.setReadFileLocation(path);
                final GCView localGCView = new GCView(localGCDocument);
                localGCDocument.setView(localGCView);
                MyJInternalFrame localMyJInternalFrame = new MyJInternalFrame(localGCDocument.getName(), false);
                localMyJInternalFrame.setDefaultCloseOperation(2);
                localGCView.initialize(this, localMyJInternalFrame);
                localGCView.setScale(localGCDocument.currentScale);
                if (localGCDocument.point != null) {
                    localGCView.setViewPosition(localGCDocument.point);
                }
                localMyJInternalFrame.setBounds(localGCDocument.bounds);
                localMyJInternalFrame.addInternalFrameListener(new InternalFrameListener() {
                    public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                        Editor.myCurrentView = localGCView;
                        Editor.compilationView = true;
                        Editor.topLevelView = true;
                        localGCView.requestFocus();
                        AppAction.updateAllActions();
                    }

                    public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }

                    public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }

                    public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                        Editor.this.closeMacros(localGCView.getDoc());
                        Editor.topGrafcharts.remove(localGCView.getDoc());
                        Editor.compilationView = false;
                        Editor.myCurrentView = null;
                        AppAction.updateAllActions();
                    }

                    public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }

                    public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }

                    public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {
                    }
                });
                localMyJInternalFrame.getContentPane().setLayout(new MyBorderLayout());
                localMyJInternalFrame.getContentPane().add(localGCView);
                getDesktop().add(localMyJInternalFrame);
                localMyJInternalFrame.show();
                localGCView.initializeDragDropHandling();
                localGCDocument.setSuspendUpdates(false);
                if (!localGCDocument.modificationProperty) {
                    localGCDocument.setModificationProperty(false);
                }
                localGCView.hasHorizontalScrollBar = localGCDocument.horizontalScrollBar;
                if (!localGCDocument.horizontalScrollBar) {
                    localGCView.setHorizontalScrollBar(null);
                }
                localGCView.hasVerticalScrollBar = localGCDocument.verticalScrollBar;
                if (!localGCDocument.verticalScrollBar) {
                    localGCView.setVerticalScrollBar(null);
                }
            }
        }
    }

    private void assistantAction() {
        if (!objectHelp("Editor_EditMenu_Assitant")) { //A ajouter aux helps
            GCView localGCView = myCurrentView;
            if (localGCView != null) {
                new MyJDialog("Assitant").setVisible(true);
            }
        }
    }

    private void modeStandardAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void modeSimulationAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void connectArduinoAction() {
        if (!objectHelp("Editor_AutomateMenu_Connect")) {
            new ConnectDialog(this, null).setVisible(true);       
            updateActions();              
        }
    }

    private void disconnectArduinoAction() {
        if (!objectHelp("Editor_AutomateMenu_Disconnect")) {
            Editor.myBoard.setConnected(false);
            updateActions();              
        }
    }

    public void variablesEditorAction(int selectedTab) {
        if (!objectHelp("Editor_AutomateMenu_Variables")) {
            GCDocument localGCDocument;
            for (localGCDocument = (GCDocument) myCurrentView.getDocument(); localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {}
            
            new VariableEditorDialog(myCurrentView.getFrame(), localGCDocument, selectedTab).setVisible(true);       
            //updateActions();              
        }
    }

    private void televerserAction() {
        if (!objectHelp("Editor_AutomateMenu_Televerser")) {
            try {
                GCDocument gcDoc;
                for (gcDoc = (GCDocument) myCurrentView.getDocument(); gcDoc.owner != null; gcDoc = gcDoc.owner.getDocument()) {}
                addUniqueNames(gcDoc, 0);
                gcDoc.initializeDocument2(gcDoc);
                new CGeneretor(gcDoc).generate("../arduino/compile_dir/source.cpp");
                CGeneretor.updateMakefileVariables();
                
                //Exécuter le makefile
                Process cmdProc = Runtime.getRuntime().exec("make", null, new File("../arduino"));
                
                BufferedReader stdoutReader = new BufferedReader(
                        new InputStreamReader(cmdProc.getInputStream()));
                String line;
                while ((line = stdoutReader.readLine()) != null) {
                    System.out.println(line);
                }
                
                /*
                BufferedReader stderrReader = new BufferedReader(
                        new InputStreamReader(cmdProc.getErrorStream()));
                while ((line = stderrReader.readLine()) != null) {
                    // process procs standard error here
                }
                */      
                
                cmdProc.waitFor();
                //int retValue = cmdProc.exitValue();
            } catch (IOException ex) {
                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void exportArduinCodeAction() {
        if (!objectHelp("Editor_AutomateMenu_ExportArduinCode")) {
            GCDocument gcDoc;
            for (gcDoc = (GCDocument) myCurrentView.getDocument(); gcDoc.owner != null; gcDoc = gcDoc.owner.getDocument()) {}
            addUniqueNames(gcDoc, 0);
            addUniqueNames(gcDoc, 0);
            gcDoc.initializeDocument2(gcDoc);
            
            File docFile = new File(gcDoc.getWriteFileLocation());            
            File dir = new File(docFile.getAbsolutePath()+"_Arduino");
            dir.mkdir();
            File arduinoFile = new File(dir, dir.getName()+".ino");
            //arduinoFile.createNewFile();
            
            new CGeneretor(gcDoc).generate(arduinoFile.getAbsolutePath());
            updateActions();
        }
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Editor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
