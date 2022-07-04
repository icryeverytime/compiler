/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Christian
 */
public class SwingDemo {

    private final DefaultMutableTreeNode root;
    public SwingDemo(String data) {
        this.root = new DefaultMutableTreeNode(data);
    }
    
    public DefaultMutableTreeNode addChildRoot(String child) {
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(child);
        this.root.add(node1);
        System.out.println();
        return node1;
    }
    public DefaultMutableTreeNode returnRoot()
    {
        return this.root;   
    }
    public DefaultMutableTreeNode returnLastChild()
    {
        return (DefaultMutableTreeNode) this.root.getLastChild();
    }
    public DefaultMutableTreeNode returnListaDeclaracion()
    {
        DefaultMutableTreeNode one=(DefaultMutableTreeNode) this.root.getLastChild();
        DefaultMutableTreeNode dos=(DefaultMutableTreeNode) one.getLastChild();
        DefaultMutableTreeNode tres=(DefaultMutableTreeNode) dos.getLastChild();
         DefaultMutableTreeNode cuatro=(DefaultMutableTreeNode) tres.getLastChild();
         DefaultMutableTreeNode cinco=(DefaultMutableTreeNode) cuatro.getPreviousSibling();
        return cinco;
        
    }
    public DefaultMutableTreeNode insertlistadeclaracion(String signo)
    {
                
                    DefaultMutableTreeNode node4 = (DefaultMutableTreeNode) this.root.getLastChild();
                    DefaultMutableTreeNode node6 = new DefaultMutableTreeNode("lista-declaracion");
                    DefaultMutableTreeNode node7 = new DefaultMutableTreeNode("tipo");
                    DefaultMutableTreeNode node8 = new DefaultMutableTreeNode("lista-id");
                    DefaultMutableTreeNode node9 = new DefaultMutableTreeNode(";");
                    DefaultMutableTreeNode node10 = new DefaultMutableTreeNode("declaracion");
                    DefaultMutableTreeNode node11;
                    node11 = new DefaultMutableTreeNode(signo);
                    
                    node4.add(node6);
                    return node6;
        
    }
    
     /* JFrame frame = new JFrame("Demo");
      DefaultMutableTreeNode node = new DefaultMutableTreeNode("Project");
      DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("App");
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
      node3.add(six);
      JTree tree = new JTree(node);
      frame.add(tree);
      frame.setSize(550,400);
      frame.setVisible(true);*/
   
}
