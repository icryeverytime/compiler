/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Charly Ponce
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void analizarLexico() throws IOException{
        int cont = 1;
        //TreeNode<String> root;
        //root = new TreeNode<String>("program");
        String expr = (String) txtResultado.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "";
        String text="";
        SwingDemo root = new SwingDemo("arbol");
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Arbol");
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Program");
      /*DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("App");
      DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Website");
      DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("WebApp");
      node.add(node1);
      node.add(node2);
      node.add(node3);
      DefaultMutableTreeNode one = new DefaultMutableTreeNode("Learning website");
      DefaultMutableTreeNode two = new DefaultMutableTreeNode("Business website");
      DefaultMutableTreeNode three = new DefaultMutableTreeNode("News publishing website");
      DefaultMutableTreeNode four = new DefaultMutableTreeNode("Android app");
      DefaultMutableTreeNode five = new DefaultMutableTreeNode("iOS app");
      DefaultMutableTreeNode six = new DefaultMutableTreeNode("Editor WebApp");
      node1.add(one);
      node1.add(two);
      node1.add(three);
      node2.add(four);
      node2.add(five);
      node3.add(six);*/
      String lasttoken="";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtAnalizarLex.setText(resultado);
                //jTextArea1.setText(text);
                
                JTree tree=new JTree(root.returnRoot());
                frame.add(tree);
                frame.setSize(850,700);
                frame.setVisible(true);
                return;
            }
            switch (token) {
                case Linea:
                    cont++;
                    break;
                case Comillas:
                    resultado += "Linea " + cont+":";
                    resultado += " Comillas\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Comentarios:
                    resultado += "Linea " + cont+":";
                    resultado += " Comentarios\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Multi_comentario_a:
                    resultado += "Linea " + cont+":";
                    resultado += " Multicomentario abriendo\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Multi_comentario_b:
                    resultado += "Linea " + cont+":";
                    resultado += " Multicomnetario cerrando\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Program:
                    resultado += "Linea " + cont+":";
                    resultado += " Program\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    DefaultMutableTreeNode lastchild=root.addChildRoot("program");
                    DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("{"); 
                    DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("Program");
                    lastchild.add(node3);
                    lastchild.add(node2);
                    lasttoken="program";
                    break;
                case Bool:
                    resultado += "Linea " + cont+":";
                    resultado += " Bool\t" +"Simbolo: "+ lexer.lexeme + "\n";
                     
                    
                    lasttoken="lista-declaracion";
                    break;
                case Write:
                    resultado += "Linea " + cont+":";
                    resultado += " Write\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Read:
                    resultado += "Linea " + cont+":";
                    resultado += " Read\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Until:
                    resultado += "Linea " + cont+":";
                    resultado += " Until\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Fi:
                    resultado += "Linea " + cont+":";
                    resultado += " Fi\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                 case Coma:
                    resultado += "Linea " + cont+":";
                    resultado += " Coma\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Then:
                    resultado += "Linea " + cont+":";
                    resultado += " Then\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Punto:
                    resultado += "Linea " + cont+":";
                    resultado += " Punto\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Cadena:
                    resultado += "Linea " + cont+":";
                    resultado += "  Tipo de dato\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case T_dato:
                    resultado += "Linea " + cont+":";
                    resultado += "  Tipo de dato\t" +"Simbolo: "+ lexer.lexeme + "\n";
                   
                    DefaultMutableTreeNode node6=root.insertlistadeclaracion(lexer.lexeme);
                    DefaultMutableTreeNode node7 = new DefaultMutableTreeNode("tipo");
                    DefaultMutableTreeNode node8 = new DefaultMutableTreeNode("lista-id");
                    DefaultMutableTreeNode node9 = new DefaultMutableTreeNode(";");
                    DefaultMutableTreeNode node10 = new DefaultMutableTreeNode("declaracion");
                    DefaultMutableTreeNode node11;
                    node11 = new DefaultMutableTreeNode(lexer.lexeme);
                    node6.add(node10);
                    node10.add(node7);
                    node10.add(node8);
                    node10.add(node9);
                    node7.add(node11);
                    
                    lasttoken="lista-declaracion";
                    break;
                case If:
                    resultado += "Linea " + cont+":";
                    resultado += "  Reservada if\t"+"Simbolo: " + lexer.lexeme + "\n";
                    break;
                case Else:
                    resultado += "Linea " + cont+":";
                    resultado += "  Reservada else\t"+"Simbolo: " + lexer.lexeme + "\n";
                    break;
                case Do:
                    resultado += "Linea " + cont+":";
                    resultado += "  Reservada do\t"+"Simbolo: " + lexer.lexeme + "\n";
                    break;
                case While:
                    resultado += "Linea " + cont+":";
                    resultado += "  Reservada while\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case For:
                    resultado += "Linea " + cont+":";
                    resultado += "  Reservada while\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Igual:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador igual\t"+"Simbolo: " + lexer.lexeme + "\n";
                    break;
                case Suma:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador suma\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Resta:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador resta\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Multiplicacion:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador multiplicacion\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Division:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador division\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Op_logico:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador logico\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Op_incremento:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador incremento\t"+"Simbolo: " + lexer.lexeme + "\n";
                    break;
                case Op_relacional:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador relacional\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Op_atribucion:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador atribucion\t"+"Simbolo: " + lexer.lexeme + "\n";
                    break;
                case Op_booleano:
                    resultado += "Linea " + cont+":";
                    resultado += "  Operador booleano\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Parentesis_a:
                    resultado += "Linea " + cont+":";
                    resultado += "  Parentesis de apertura\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Parentesis_c:
                    resultado += "Linea " + cont+":";
                    resultado += "  Parentesis de cierre\t"+"Simbolo: " + lexer.lexeme + "\n";
                    break;
                case Llave_a:
                    resultado += "Linea " + cont+":";
                    resultado += "  Llave de apertura\t"+"Simbolo: " + lexer.lexeme + "\n";
                    break;
                case Llave_c:
                    resultado += "Linea " + cont+":";
                    resultado += "  Llave de cierre\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    
                    
                        DefaultMutableTreeNode last = root.returnLastChild();
                        DefaultMutableTreeNode llave = new DefaultMutableTreeNode("}"); 
                      
                    last.add(llave);
                    
                    break;  
                case Corchete_a:
                    resultado += "Linea " + cont+":";
                    resultado += "  Corchete de apertura\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Corchete_c:
                    resultado += "Linea " + cont+":";
                    resultado += "  Corchete de cierre\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case Main:
                    resultado += "Linea " + cont+":";
                    resultado += "  Reservada main\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case P_coma:
                    resultado += "Linea " + cont+":";
                    resultado += "  Punto y coma\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    lasttoken="newline";
                    break;
                case Identificador:
                    resultado += "Linea " + cont+":";
                    resultado += "  Identificador\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    
                    text+="Program\n Program {";
                    if(lasttoken.contains("lista-declaracion"))
                    {
                        
                        DefaultMutableTreeNode declast = root.returnListaDeclaracion();
                        DefaultMutableTreeNode declast2 = new DefaultMutableTreeNode(lexer.lexeme);
                        declast.add(declast2);
                    }
                    lasttoken="lista-declaracion";
                    break;
                case Numero:
                    resultado += "Linea " + cont+":";
                    resultado += "  Numero\t" +"Simbolo: "+ lexer.lexeme + "\n";
                    break;
                case ERROR:
                    resultado += "Linea " + cont+":";
                    resultado += "  Simbolo no definido\n";
                    break;
                default:
                    resultado += "Linea " + cont+":";
                    resultado += "  < " + lexer.lexeme + " >\n";
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnalizarLex = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnalizarSin = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18)));

        txtResultado.setColumns(20);
        txtResultado.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        jLabel2.setText("New File");

        txtAnalizarLex.setEditable(false);
        txtAnalizarLex.setColumns(20);
        txtAnalizarLex.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        txtAnalizarLex.setRows(5);
        jScrollPane2.setViewportView(txtAnalizarLex);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lexico", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Syntax Tree", jPanel4);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane5.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Semantico", jPanel5);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane6.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Codigo Intermedio", jPanel6);

        jLabel1.setText("Results:");

        txtAnalizarSin.setEditable(false);
        txtAnalizarSin.setColumns(20);
        txtAnalizarSin.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        txtAnalizarSin.setRows(5);
        jScrollPane3.setViewportView(txtAnalizarSin);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jMenuItem3.setText("Open");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Compile");

        jMenuItem2.setText("Compile");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        try {
            String ST = txtResultado.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));
            analizarLexico();
             
        
        try {
            s.parse();
            txtAnalizarSin.setText("Program has compile successfully");
            txtAnalizarSin.setForeground(new Color(25, 111, 61));
        } catch (Exception ex) {
            Symbol sym = s.getS();
            txtAnalizarSin.setText("Error of sintaxis. Line: " + (sym.right + 1) + " Column: " + (sym.left + 1) + ", Text: \"" + sym.value + "\"");
            txtAnalizarSin.setForeground(Color.red);
            frame.setVisible(false);
        }
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
        
        try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtResultado.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }
JFrame frame=new JFrame("arbol");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea txtAnalizarLex;
    private javax.swing.JTextArea txtAnalizarSin;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
