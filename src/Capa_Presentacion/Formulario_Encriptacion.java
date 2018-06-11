package Capa_Presentacion;
import Capa_Datos.Cliente;
import Capa_Datos.Cuenta;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Edwin
 */
public class Formulario_Encriptacion extends javax.swing.JFrame {

    private String tabla = "abcdefghijklmnñopqrstuvwxyzáéíóúABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ1234567890.,;_:+-*/ @$€#¿?!¡=()[]{}\\\""; // Se guardan los caracteres para cifrar o descifrar el texo y la clave que se usara
  
    public Formulario_Encriptacion() { 
        initComponents();
        ListaCliente();
        ListaCuenta();
        jButton4.setEnabled(false);
        jButton8.setEnabled(false);

       
    }
    private String Limpiar_texto(String texto) // limpia el texto introducido de caracteres desconocidos que no estan dentro de la tabla 
     {
         texto = texto.replaceAll("\n", "");  // se eliminan todos los saltos de linea del texto sustituyendolo por una cadena vacia
         
        for(int x = 0; x < texto.length(); x++)// para eliminar los posibles caracteres desocnocidos del texto  serecorre con este bucle for 
        {
            int posicion = tabla.indexOf(texto.charAt(x)); //y con  el metodo indexOF se va guardando en una variable llamada posicion el indice que corresponde a la tablla de carcteres
            // si el metodo indexOF no encuentra el indice se indicara que el caracter no esta,
            
            if (posicion == -1) // Significa que el caracter no esta en la tabla 
            {
                texto = texto.replace(texto.charAt(x), ' '); // con replace se sustituye el caracter por un espacio en blanco
            }
        }        
        return texto; //indica que devuelve el texto
    } 
       
    public  String Encriptar(String texto,String clave) // Se define el metodo cifrar con los argumentos del texto a cifrar la clave 
    {
      String texto_limpioo = Limpiar_texto(texto);  // Con el metodo limpiar Se limpia el texto que se quiere cifrar y se guarda en la variable texto limpio

      String cifrado = "";// variable para guardar el texto ya cifrado inicializada a cadena vacia
     for(int t = 0,k= 0; t < texto_limpioo.length(); t++,k= (k+1) % clave.length())  
    //para ir sustituyendo cada caracter del texto a cifrar por el carcater situado en otra posicion, en funcion de la clave introducida
    // este  bucle for se creo con dos variables para  para recorrer el texto y la clave
    // con la varible T se recorre el texto, con la variable C, se recorre la clave , se icremente de 1 en uno la funcion length se utiliza para incrementar  
      {
        int posicion = (tabla.indexOf(texto_limpioo.charAt(t)) + tabla.indexOf(clave.charAt(k))) % tabla.length(); // el indexOf se busca el indice de cada caracter que se quiere cifrar
        // en la tabla de caracteres, y se le suma el indice de cada caracter de la clave en la tabla y se guarda en una varible llamada posicion usando la funcion midulo "length" para no sobrepasar
        // la longitud de la tabla. 

       cifrado += tabla.charAt(posicion ); // con el metodo Char se va guardando el cifrado del caracter de la tabla situado en una posicion calculada.

      }
       return cifrado; //devuelve el cifrado.
    }

    public String Desencriptar(String texto,String clave  )// Metodo descifrar con el texto a descifrar y la clave este hace lo contrario al metodo cifrar
            //
    {
      String texto_limpioo = Limpiar_texto(texto);  // para limpiar el texto que se quiere descifrar y se guarda en una variable llamada texto limpio

      String descifrado = "";  //Guarda el texto ya descifrado inicializada a cadena vacia 

      for(int t = 0, c = 0; t < texto_limpioo.length(); t++, c = (c +1) % clave .length()) //con el bucle for se recorre el texto a descifrar en la clave 
      {
       int posicion = (tabla.indexOf(texto_limpioo.charAt(t)) - tabla.indexOf(clave.charAt(c))); //Utilizando el metodo indexOf se resta a cada indice 
       //el caracter cifrado del indice correspondiente del caracter de la clave en la tabla de caracteres y se guarda en posición  

       posicion = (posicion < 0)?(posicion + tabla.length()): posicion; // se valida si posicion en menor que cero se suma el numero total de caracteres de
       // tabla que se obtiene con el metodo length en caso contrario se deja la misma posicion que se ha calculado.

       descifrado += tabla.charAt(posicion); // el metodo char se va guardando en descifrado el caracter de la tabla situado en la posicion calculada
       }
      return descifrado; // Devuelve descifrado
    }
    
    public void ListaCliente()
    {
        DefaultTableModel tabla=new DefaultTableModel();
        Cliente objart=new Cliente();
        ArrayList<Cliente> lista2=new ArrayList();
        lista2=objart.ListaCliente();
        tabla.addColumn("ID Cliente");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Direccion");
        tabla.addColumn("Telefono");
        tabla.setRowCount(lista2.size());
        int i=0;
        for(Cliente x:lista2)
            {
                tabla.setValueAt(x.getId_cliente(), i, 0);
                tabla.setValueAt(x.getNombrec(), i, 1);
                tabla.setValueAt(x.getApellido1(), i, 2);
                tabla.setValueAt(x.getDireccion(), i, 3);
                tabla.setValueAt(x.getTelefono (), i, 4);
                i++;
            }
        this.jTable2.setModel(tabla);
    }

    public void ListaCuenta()
    {
        DefaultTableModel tabla=new DefaultTableModel();
        Cuenta objart=new Cuenta();
        ArrayList<Cuenta> lista3=new ArrayList();
        lista3=objart.ListaCuenta();
        tabla.addColumn("Numero");
        tabla.addColumn("Saldo");
        tabla.addColumn("Clave");
        tabla.setRowCount(lista3.size());
        int i=0;
        for(Cuenta x:lista3)
            {
                tabla.setValueAt(x.getNumero(), i, 0);
                tabla.setValueAt(x.getSaldo(), i, 1);
                tabla.setValueAt(x.getClave(), i, 2);
                i++;
            }
        this.jTable3.setModel(tabla);
    }
    public void Limpiarcliente()
    {
        this.jTCli.setText("");
        this.jTNom.setText("");
        this.jTAp.setText("");
        this.jTDir.setText("");
        this.jTTel.setText("");
    }
    
    public void Limpiarcuenta()
    {
        this.jTNum.setText("");
        this.jTSal.setText("");
        this.jTClav.setText("");
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTexDes = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTexEnc = new javax.swing.JTextArea();
        jBGdr = new javax.swing.JButton();
        jBEnc = new javax.swing.JButton();
        jBDes = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTCli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTNom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTAp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTDir = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTTel = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTNum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTSal = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTClav = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Texto Entrada"));

        jTexDes.setColumns(20);
        jTexDes.setRows(5);
        jTexDes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTexDesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTexDes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Texto Salida"));

        jTexEnc.setColumns(20);
        jTexEnc.setRows(5);
        jTexEnc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTexEncFocusGained(evt);
            }
        });
        jTexEnc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTexEncMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTexEnc);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBGdr.setText("Guardar");

        jBEnc.setText("Encriptar");
        jBEnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEncActionPerformed(evt);
            }
        });

        jBDes.setText("Desencriptar");
        jBDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDesActionPerformed(evt);
            }
        });

        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel2.setText("ID");

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellido");

        jLabel5.setText("Direccion");

        jLabel6.setText("Telefono");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Borrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTTel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTDir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTCli)
                                .addComponent(jTNom)
                                .addComponent(jTAp, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(16, 16, 16)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTAp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuenta"));

        jLabel7.setText("Numero");

        jLabel8.setText("Saldo");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(jTable3);

        jButton6.setText("Nuevo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Guardar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Borrar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(36, 36, 36)
                                .addComponent(jTNum, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(36, 36, 36)
                                .addComponent(jTSal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTSal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)))
        );

        jLabel1.setText("Clave");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBEnc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBDes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBGdr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(jTClav)
                        .addGap(31, 31, 31))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTClav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBEnc)
                    .addComponent(jBDes)
                    .addComponent(jBGdr)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int r=JOptionPane.showConfirmDialog(null,"Esta Seguro?");
        if(r==0)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTexDesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTexDesMouseClicked
        jTexEnc.setEnabled(false);
        jTexDes.setEnabled(true);
    }//GEN-LAST:event_jTexDesMouseClicked

    private void jTexEncMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTexEncMouseClicked
        jTexDes.setEnabled(false);
        jTexEnc.setEnabled(true);
    }//GEN-LAST:event_jTexEncMouseClicked

    private void jBEncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEncActionPerformed
        String texto = jTexDes.getText();
        
        String clave = jTClav.getText();
        
        jTexEnc.setText(Encriptar(texto,clave ));
    }//GEN-LAST:event_jBEncActionPerformed

    private void jTexEncFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTexEncFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTexEncFocusGained

    private void jBDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDesActionPerformed
        String texto = jTexDes.getText();
        
        String clave = jTClav.getText();
        
        jTexEnc.setText(Desencriptar(texto,clave ));
    }//GEN-LAST:event_jBDesActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Cliente objart=new Cliente();
        objart.setId_cliente(this.jTCli.getText());
        objart.setNombrec(this.jTNom.getText());
        objart.setApellido1(this.jTAp.getText());
        objart.setDireccion(this.jTDir.getText());
        objart.setTelefono(this.jTTel.getText());
        JOptionPane.showMessageDialog(null, objart.GrabarCliente());
        ListaCliente();
        Limpiarcliente();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Cuenta objart=new Cuenta();
        objart.setNumero(this.jTNum.getText());
        objart.setSaldo(this.jTSal.getText());
        JOptionPane.showMessageDialog(null, objart.GrabarCuenta());
        ListaCuenta();
        Limpiarcuenta();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        int jt2=this.jTable2.getSelectedRow();
        this.jTCli.setText(jTable2.getValueAt(jt2, 0).toString());
        this.jTNom.setText(jTable2.getValueAt(jt2, 1).toString());
        this.jTAp.setText(jTable2.getValueAt(jt2, 2).toString());
        this.jTDir.setText(jTable2.getValueAt(jt2, 3).toString());
        this.jTTel.setText(jTable2.getValueAt(jt2, 4).toString());
        jButton4.setEnabled(true);
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jTable2MousePressed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
        int jt3=this.jTable3.getSelectedRow();
        this.jTNum.setText(jTable3.getValueAt(jt3, 0).toString());
        this.jTSal.setText(jTable3.getValueAt(jt3, 1).toString());
        this.jTClav.setText(jTable3.getValueAt(jt3, 2).toString());
        jButton8.setEnabled(true);
        jButton7.setEnabled(false);
    }//GEN-LAST:event_jTable3MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         Limpiarcliente();
         jButton4.setEnabled(false);
         jButton3.setEnabled(true);
         //jTCodpc.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Limpiarcuenta();
        jButton8.setEnabled(false);
        jButton7.setEnabled(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int Res=JOptionPane.showConfirmDialog(null,"Esta seguro de Eliminar el Articulo: "+this.jTCli.getText());
        if(Res==0)
        {
            Cliente objart=new Cliente();
            objart.setId_cliente(this.jTCli.getText());
            JOptionPane.showMessageDialog(null, objart.EliminarCliente());
            ListaCliente();
            JOptionPane.showMessageDialog(null, "Articulo Eliminado");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int Res=JOptionPane.showConfirmDialog(null,"Esta seguro de Eliminar el Articulo: "+this.jTNum.getText());
        if(Res==0)
        {
            Cuenta objart=new Cuenta();
            objart.setNumero(this.jTNum.getText());
            JOptionPane.showMessageDialog(null, objart.EliminarCuenta());
            ListaCuenta();
            JOptionPane.showMessageDialog(null, "Articulo Eliminado");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Formulario_Encriptacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario_Encriptacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario_Encriptacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario_Encriptacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario_Encriptacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBDes;
    private javax.swing.JButton jBEnc;
    private javax.swing.JButton jBGdr;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTAp;
    private javax.swing.JTextField jTClav;
    private javax.swing.JTextField jTCli;
    private javax.swing.JTextField jTDir;
    private javax.swing.JTextField jTNom;
    private javax.swing.JTextField jTNum;
    private javax.swing.JTextField jTSal;
    private javax.swing.JTextField jTTel;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTexDes;
    private javax.swing.JTextArea jTexEnc;
    // End of variables declaration//GEN-END:variables
}
