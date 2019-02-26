/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividadeordenacao04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alison
 */
public class AtividadeOrdenacao04 {

    private static final List<Integer> listNumeros = new ArrayList<Integer>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        addAll("src/couting.txt");

        //heapMax(listNumeros);
        heapSort(listNumeros);
    
    }

    public static void addAll(String file) {
        System.out.println("LENDO ARQUIVO");
        try {
            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                //String numeros[] = linha.split("-");
                listNumeros.add(Integer.parseInt(linha));

                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        List<Integer> arr = listNumeros;
    }

    public static void heapMax(List<Integer> arr) {
        MaxHeap maxHeap = new MaxHeap(arr.size());

        for (int i = 0; i < arr.size(); i++) {
            maxHeap.insert(arr.get(i));
        }
        maxHeap.print();
        System.out.println("The max val is " + maxHeap.extractMax());
    }

    public static void heapSort(List<Integer> arrs) {
        int arr[] = new int[arrs.size()];;
        for(int i = 0;i<arrs.size();i++){
            arr[i] = arrs.get(i);
        }
        
        HeapSort ob = new HeapSort();
        ob.sort(arr);

        System.out.println("Sorted array is");
        ob.printArray(arr);
    }

    public static class MaxHeap {

        private int[] Heap;
        private int size;
        private int maxsize;

        // Constructor to initialize an 
        // empty max heap with given maximum 
        // capacity. 
        public MaxHeap(int maxsize) {
            this.maxsize = maxsize;
            this.size = 0;
            Heap = new int[this.maxsize + 1];
            Heap[0] = Integer.MAX_VALUE;
        }

        // Returns position of parent 
        private int parent(int pos) {
            return pos / 2;
        }

        // Below two functions return left and 
        // right children. 
        private int leftChild(int pos) {
            return (2 * pos);
        }

        private int rightChild(int pos) {
            return (2 * pos) + 1;
        }

        // Returns true of given node is leaf 
        private boolean isLeaf(int pos) {
            if (pos >= (size / 2) && pos <= size) {
                return true;
            }
            return false;
        }

        private void swap(int fpos, int spos) {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        // A recursive function to max heapify the given 
        // subtree. This function assumes that the left and 
        // right subtrees are already heapified, we only need 
        // to fix the root. 
        private void maxHeapify(int pos) {
            if (isLeaf(pos)) {
                return;
            }

            if (Heap[pos] < Heap[leftChild(pos)]
                    || Heap[pos] < Heap[rightChild(pos)]) {

                if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }

        // Inserts a new element to max heap 
        public void insert(int element) {
            Heap[++size] = element;

            // Traverse up and fix violated property 
            int current = size;
            while (Heap[current] > Heap[parent(current)]) {
                swap(current, parent(current));
                current = parent(current);
            }
        }

        public void print() {
            for (int i = 1; i <= size / 2; i++) {
                System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : "
                        + Heap[2 * i] + " RIGHT CHILD :" + Heap[2 * i + 1]);
                System.out.println();
            }
        }

        // Remove an element from max heap 
        public int extractMax() {
            int popped = Heap[1];
            Heap[1] = Heap[size--];
            maxHeapify(1);
            return popped;
        }
    }

    public static class HeapSort {

        public void sort(int arr[]) {
            int n = arr.length;

            // Build heap (rearrange array) 
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }

            // One by one extract an element from heap 
            for (int i = n - 1; i >= 0; i--) {
                // Move current root to end 
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // call max heapify on the reduced heap 
                heapify(arr, i, 0);
            }
        }

        // To heapify a subtree rooted with node i which is 
        // an index in arr[]. n is size of heap 
        void heapify(int arr[], int n, int i) {
            int largest = i; // Initialize largest as root 
            int l = 2 * i + 1; // left = 2*i + 1 
            int r = 2 * i + 2; // right = 2*i + 2 

            // If left child is larger than root 
            if (l < n && arr[l] > arr[largest]) {
                largest = l;
            }

            // If right child is larger than largest so far 
            if (r < n && arr[r] > arr[largest]) {
                largest = r;
            }

            // If largest is not root 
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree 
                heapify(arr, n, largest);
            }
        }

        /* A utility function to print array of size n */
        void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

}
