package grafchart.sfc;

import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import grafchart.graphics.MyJDialog;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Inspector {

    private static MyJDialog myDialog = null;
    private static JTable myTable = null;
    private static InspectorTableModel myModel = null;

    public static void inspect(Object paramObject, GCView paramGCView) {
        try {
            myModel = new InspectorTableModel();
            myModel.setObject(paramObject);
            myTable = new JTable(myModel);
            myModel.setTable(myTable);
            myTable.setPreferredScrollableViewportSize(new Dimension(300, Math.min(500, myModel.getRowCount() * 16)));
            myTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent paramAnonymousMouseEvent) {
                    Inspector.myModel.mouseClicked(paramAnonymousMouseEvent.getClickCount(), paramAnonymousMouseEvent.getButton(), Inspector.myTable.rowAtPoint(paramAnonymousMouseEvent.getPoint()), Inspector.myTable.columnAtPoint(paramAnonymousMouseEvent.getPoint()));
                }
            });
            JScrollPane localJScrollPane = new JScrollPane(myTable);
            Referencable localReferencable = (Referencable) paramObject;
            MyJDialog localMyJDialog = new MyJDialog(Editor.singleton, localReferencable.getFullName(), false);
            localMyJDialog.getContentPane().setLayout(new BorderLayout());
            localMyJDialog.getContentPane().add(localJScrollPane, "Center");
            localMyJDialog.setLocationRelativeTo(paramGCView);
            localMyJDialog.pack();
            localMyJDialog.setVisible(true);
            myDialog = localMyJDialog;
            myTable.getColumnModel().getColumn(0).setPreferredWidth(35);
            myDialog.setVisible(true);
        } catch (Exception localException) {
            Utils.writeException(localException);
        }
    }

    public static void refresh() {
        if ((myModel != null) && (myDialog != null) && (myDialog.isVisible())) {
            myModel.setObject(myModel.getObject());
        }
    }

    public static JDialog getInspector() {
        return myDialog;
    }

    public static class InspectorTableModel extends AbstractTableModel {

        private Object myObject = null;
        private ArrayList myReferences = new ArrayList();
        private JTable minTable = null;

        public Object getObject() {
            return this.myObject;
        }

        public void setObject(Object paramObject) {
            this.myObject = paramObject;
            this.myReferences = new ArrayList();
            if (this.myObject != null) {
                addReferences(this.myObject);
            }
            fireTableChanged(new TableModelEvent(this));
        }

        public String getColumnName(int paramInt) {
            if (paramInt == 0) {
                return "Name";
            }
            return "Value";
        }

        public int getColumnCount() {
            return 2;
        }

        public int getRowCount() {
            return this.myReferences.size();
        }

        public void setValueAt(Object paramObject, int paramInt1, int paramInt2) {
            String str = (String) paramObject;
            JGoObject localJGoObject = (JGoObject) this.myReferences.get(paramInt1);
            if ((localJGoObject.getDocument() == null) || (localJGoObject.getDocument().isModifiable())) {
                try {
                    Object localObject;
                    if ((localJGoObject instanceof StringVariable)) {
                        localObject = (StringVariable) localJGoObject;
                        if (!((StringVariable) localObject).isConstant()) {
                            ((StringVariable) localObject).setStoredStringAction(str);
                            fireTableChanged(new TableModelEvent(this));
                            return;
                        }
                    }
                    if ((localJGoObject instanceof RealVariable)) {
                        localObject = (RealVariable) localJGoObject;
                        if (!((RealVariable) localObject).isConstant()) {
                            ((RealVariable) localObject).setStoredRealAction(Double.parseDouble(str));
                            fireTableChanged(new TableModelEvent(this));
                            return;
                        }
                    }
                    if ((localJGoObject instanceof AnalogOut)) {
                        localObject = (AnalogOut) localJGoObject;
                        ((AnalogOut) localObject).setStoredRealAction(Double.parseDouble(str));
                        fireTableChanged(new TableModelEvent(this));
                        return;
                    }
                    if ((localJGoObject instanceof AnalogIn)) {
                        localObject = (AnalogIn) localJGoObject;
                        ((AnalogIn) localObject).setValue(Double.parseDouble(str));
                        fireTableChanged(new TableModelEvent(this));
                        return;
                    }
                    if ((localJGoObject instanceof IntegerVariable)) {
                        localObject = (IntegerVariable) localJGoObject;
                        if (!((IntegerVariable) localObject).isConstant()) {
                            ((IntegerVariable) localObject).setStoredIntAction(Integer.parseInt(str));
                            fireTableChanged(new TableModelEvent(this));
                            return;
                        }
                    }
                    if ((localJGoObject instanceof BooleanVariable)) {
                        localObject = (BooleanVariable) localJGoObject;
                        if (!((BooleanVariable) localObject).isConstant()) {
                            if (str.equals("1")) {
                                ((BooleanVariable) localObject).setStoredBoolAction(true);
                            } else {
                                ((BooleanVariable) localObject).setStoredBoolAction(false);
                            }
                            fireTableChanged(new TableModelEvent(this));
                            return;
                        }
                    }
                    if ((localJGoObject instanceof DigitalIn)) {
                        ((DigitalIn) localJGoObject).setValue(!str.equals("0"));
                        return;
                    }
                } catch (Exception localException) {
                }
            }
        }

        public Object getValueAt(int paramInt1, int paramInt2) {
            Referencable localReferencable = (Referencable) this.myReferences.get(paramInt1);
            if (paramInt2 == 0) {
                return localReferencable.getName();
            }
            try {
                Object localObject;
                if ((localReferencable instanceof InternalVariable)) {
                    localObject = (InternalVariable) localReferencable;
                    if ((localObject instanceof BooleanVariable)) {
                        if (((InternalVariable) localObject).getBoolVal()) {
                            return "1";
                        }
                        return "0";
                    }
                    if ((localObject instanceof IntegerVariable)) {
                        return Integer.toString(((InternalVariable) localObject).getIntVal());
                    }
                    if ((localObject instanceof RealVariable)) {
                        return RealVariable.df.format(((InternalVariable) localObject).getRealVal());
                    }
                    if ((localObject instanceof StringVariable)) {
                        return ((InternalVariable) localObject).getStringVal();
                    }
                    return "";
                }
                if ((localReferencable instanceof AnalogIn)) {
                    localObject = (AnalogIn) localReferencable;
                    return RealVariable.df.format(((AnalogIn) localObject).getRealVal());
                }
                if ((localReferencable instanceof AnalogOut)) {
                    localObject = (AnalogOut) localReferencable;
                    return RealVariable.df.format(((AnalogOut) localObject).getRealVal());
                }
                if ((localReferencable instanceof DigitalIn)) {
                    localObject = (DigitalIn) localReferencable;
                    return ((DigitalIn) localObject).getValue() ? "1" : "0";
                }
                if ((localReferencable instanceof DigitalOut)) {
                    localObject = (DigitalOut) localReferencable;
                    if (((DigitalOut) localObject).getBoolVal()) {
                        return "1";
                    }
                    return "0";
                }
                if ((localReferencable instanceof Hierarchical)) {
                    localObject = (Hierarchical) localReferencable;
                    if ((localObject instanceof WorkspaceObject)) {
                        return "Workspace Object";
                    }
                    if ((localObject instanceof ProcedureStep)) {
                        return "ProcedureStep";
                    }
                    if ((localObject instanceof MacroStep)) {
                        return "Macro Step";
                    }
                    if ((localObject instanceof XMLMessageIn)) {
                        return "XML In";
                    }
                    if ((localObject instanceof XMLMessageOut)) {
                        return "XML out";
                    }
                    return "Internal Error: Unhandled class " + localObject.getClass().getName();
                }
                if ((localReferencable instanceof GrafcetProcedure)) {
                    return "Procedure";
                }
                if ((localReferencable instanceof BasicList)) {
                    return "List";
                }
                return "";
            } catch (Exception localException) {
                return localException.toString();
            }
        }

        public boolean isCellEditable(int paramInt1, int paramInt2) {
            Object localObject = this.myReferences.get(paramInt1);
            if (localObject == null) {
                return false;
            }
            boolean bool = true;
            if (paramInt2 != 1) {
                bool = false;
            } else if (((localObject instanceof Hierarchical)) || ((localObject instanceof GrafcetProcedure))) {
                bool = false;
            } else if ((((JGoObject) localObject).getDocument() != null) && (!((JGoObject) localObject).getDocument().isModifiable())) {
                bool = false;
            } else if ((localObject instanceof DigitalOut)) {
                bool = false;
            } else if ((((localObject instanceof BooleanVariable)) && (((BooleanVariable) localObject).isConstant())) || (((localObject instanceof IntegerVariable)) && (((IntegerVariable) localObject).isConstant())) || (((localObject instanceof RealVariable)) && (((RealVariable) localObject).isConstant())) || (((localObject instanceof StringVariable)) && (((StringVariable) localObject).isConstant()))) {
                bool = false;
            } else if ((localObject instanceof BasicList)) {
                bool = false;
            }
            return bool;
        }

        public void mouseClicked(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
            if ((paramInt3 != -1) && (paramInt4 == 1) && (paramInt1 >= 2) && (paramInt2 == 1)) {
                Object localObject = this.myReferences.get(paramInt3);
                if ((localObject != null) && (((localObject instanceof Hierarchical)) || ((localObject instanceof GrafcetProcedure))) && ((!(localObject instanceof ProcedureStep)) || (((ProcedureStep) localObject).myContentDocument != null))) {
                    setObject(localObject);
                    this.minTable.setPreferredScrollableViewportSize(new Dimension(300, Math.min(500, getRowCount() * 16)));
                    Referencable localReferencable = (Referencable) localObject;
                    Inspector.myDialog.setTitle(localReferencable.getFullName());
                    Inspector.myDialog.pack();
                }
            }
        }
        

        private void addReferences(Object paramObject) {
            GCDocument localGCDocument = null;
            Object localObject1;
            Object localObject2;
            if ((paramObject instanceof LabCommObject)) {
                localObject1 = (LabCommObject) paramObject;
                localObject2 = ((LabCommObject) localObject1).getSymbolTable().iterator();
                while (((Iterator) localObject2).hasNext()) {
                    Referencable localReferencable = (Referencable) ((Iterator) localObject2).next();
                    addReference(localReferencable);
                }
            } else {
                if ((paramObject instanceof Hierarchical)) {
                    localObject1 = (Hierarchical) paramObject;
                    localGCDocument = ((Hierarchical) localObject1).getContentDocument();
                } else if ((paramObject instanceof GrafcetProcedure)) {
                    localObject1 = (GrafcetProcedure) paramObject;
                    localGCDocument = ((GrafcetProcedure) localObject1).myContentDocument;
                }
                localObject1 = localGCDocument.getFirstObjectPos();
                for (localObject2 = localGCDocument.getObjectAtPos((JGoListPosition) localObject1); (localObject2 != null) && (localObject1 != null); localObject2 = localGCDocument.getObjectAtPos((JGoListPosition) localObject1)) {
                    addReference(localObject2);
                    localObject1 = localGCDocument.getNextObjectPosAtTop((JGoListPosition) localObject1);
                }
            }
        }

        private void addReference(Object paramObject) {
            if (((paramObject instanceof InternalVariable)) || ((paramObject instanceof AnalogIn)) || ((paramObject instanceof AnalogOut)) || ((paramObject instanceof DigitalIn)) || ((paramObject instanceof DigitalOut))) {
                this.myReferences.add(paramObject);
            }
            if ((paramObject instanceof BasicList)) {
                this.myReferences.add(paramObject);
            }
            if ((paramObject instanceof Hierarchical)) {
                this.myReferences.add(paramObject);
            }
            if ((paramObject instanceof GrafcetProcedure)) {
                this.myReferences.add(paramObject);
            }
        }

        public void setTable(JTable paramJTable) {
            this.minTable = paramJTable;
        }
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Inspector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
