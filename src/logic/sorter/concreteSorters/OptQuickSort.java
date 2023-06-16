package logic.sorter.concreteSorters;

import graphics.action.AbstractAction;
import graphics.sorterGraphics.figures.Rectangle;
import graphics.sorterGraphics.VectorPanel;
import logic.sorter.AbstractSorter;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class OptQuickSort <K extends Comparable<K>> extends AbstractSorter<K> {
        public K Select(Vector<K> A, int p, int q, int i){//deve avere in input un vettore senza ripetizioni da ordinare da 0 a A.length-1
            Rectangle rect = P.highlightArea(p, q);
            int r;
            if(p == q){
                P.removeRect(rect);
                return A.get(p);
            }else{
                K x = findPivot(A, p, q);     //trova il perno ottimale
                r = partition(A, p, q, x);     //usa partition modificato con il perno ottimale

                P.removeRect(rect);

                if(i == r){
                    return A.get(r);
                }
            }
            if(i < r){
                return Select(A, p, r-1, i);//ricerca ricorsiva finchè non trovi l'elemento da posizionare
            }else{
                return Select(A, r+1, q, i);//ricerca ricorsiva finchè non trovi l'elemento da posizionare
            }
        }

        public void createPivotArray(Vector<K> A, int p, int q, Vector<K> V){
            if(q-p+1 <= 5){               //questo metodo crea un singolo sottoarray con i mediani che trova dividendo l'array in 5 parti, verrà
                V.add(median(A, p, q));  //richiamato ricorsivamente in findPivot;
            }else{

                P.addLine(p+4);

                V.add(median(A, p, p + 4));
                createPivotArray(A,(p + 5), q, V);
            }
        }

        public K median(Vector<K> A, int p, int q){//trova il mediano di un sottovettore richiamando findMin
            K k = null;
            int n = (q-p)/2;//se è pari il vettore puoi decidere se prendere l'elemento prima di metà o quello
            for(int i = 0; i <= n; i++){                 //subito dopo la metà (se è pari a metà precisa non c'è nessun elemento)
                k = findMin(A);

                P.addPin(v.indexOf(k));
                Util.waitAction(700);

                A.remove(k);
            }
            P.removeAllPins();
            return k;
        }

        public K findMin(Vector<K> A){//trova il minimo di un vettore e lo sostituisce con MAX, poi ritorna il
            K min = A.get(0);
            for(int i = 0; i <= A.size()-1; i++){
                if(A.get(i).compareTo(min) < 0){
                    min = A.get(i);
                }
            }
            return min;
        }

        public K findPivot(Vector<K> D, int p, int q){
            int n;
            Vector<K> A = new Vector<>(D.subList(p, q));//copio il vettore per non modificare l'originale D con TrovaMin che inserisce MAX_VALUE
            if((A.size()%5) > 0){           //questo if serve per dare la giusta dimensione al sottovettore infatti se n/5 = 0 comunque V deve avere
                n = A.size()/5 + 1;          //dimensione 1
            }else{
                n = (A.size()/5 > 0)? A.size()/5 : 1;
            }

            Vector<K> V = new Vector<>(n);                              //creo il vettore V a ogni chiamata

            VectorPanel<K> supportPanel = new VectorPanel<>(V, null,200, 300, 70);
            JFrame F = new JFrame();
            F.setResizable(true);
            F.setVisible(true);
            F.setSize(500, 600);
            supportPanel.setBackground(Color.white);
            Util.setLocationToTopRight(F);
            supportPanel.removeButtons();
            F.add(supportPanel);

            createPivotArray(A,0,A.size()-1,V);            //e lo riempio con i mediani che trovo via via
            P.removeAllLines();

            supportPanel.initializeSquares(V);
            Util.waitAction(2000);

            if(V.size() == 1){
                F.dispose();
                return V.get(0);                                     //se non sono arrivato ad avere un solo valore
            }else{
                F.dispose();
                return findPivot(V, 0, V.size()-1);               //faccio la chiamata ricorsiva
            }
        }

        public int partition(Vector<K> A, int p, int q, K x){//partition modificato in modo da usare come perno l'elemento x, di cui
            int i = p-1;                                         //trova l'indice all'interno dell'array attraverso il metodo TrovaIndice
            int index = A.indexOf(x);

            P.color(index, Color.RED);

            swap(A, index, q);

            for(int j = p; j <= q; j++){

                Util.waitAction(1000);
                P.addPin(j);

                if(A.get(j).compareTo(x) <= 0){
                    i += 1;
                    swap(A, i, j);
                }
            }

            P.color(i, Color.GREEN);

            P.removeAllPins();

            return i;
        }

        public void swap(Vector<K> array, int i, int p){//scambio l'elemento in posizione i con l'elemento in posizione j nel vettore A
            AbstractAction ac = P.swap(i, p);
            while(ac.isRunning()){
                System.console();
            }

            K n = array.get(i);
            array.set(i, array.get(p));
            array.set(p, n);
        }

        public void quickSort_ott(Vector<K> A, int p, int q){//versione finale ottimizzata di QuickSort che sfrutta tutte le procedure definite sopra
            if(p < q){
                K x = Select(A,p,q,(q-p+1)/2+p);
                int r = partition(A,p,q,x);

                Rectangle rect1 = P.highlightArea(p, r-1);
                Rectangle rect2 = P.highlightArea(r+1, q);

                quickSort_ott(A,p,r-1);

                P.removeRect(rect1);

                quickSort_ott(A,r+1,q);

                P.removeRect(rect2);
            }
        }

    @Override
    public void doSort() {
        quickSort_ott(v, 0, v.size()-1);
    }
}
