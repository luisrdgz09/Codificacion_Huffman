import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCoding {

     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cadena de entrada: ");
        String input = scanner.nextLine();
        HashMap<Character, String> huffmanCodes = buildHuffmanTree(input);

        System.out.println("Original String: " + input);
        System.out.println("Huffman Codes: " + huffmanCodes);
    }

    public static HashMap<Character, String> buildHuffmanTree(String input) {
        char[] charArray = input.toCharArray();

        // Calculate frequency of each character
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : charArray) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman Tree
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char c : frequencyMap.keySet()) {
            priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode combinedNode = new HuffmanNode('\0', left.frequency + right.frequency);
            combinedNode.left = left;
            combinedNode.right = right;

            priorityQueue.add(combinedNode);
        }

        // Generate Huffman Codes
        HuffmanNode root = priorityQueue.poll();
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        return huffmanCodes;
    }

    private static void generateCodes(HuffmanNode node, String code, HashMap<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.data != '\0') {
            huffmanCodes.put(node.data, code);
        }

        generateCodes(node.left, code + "0", huffmanCodes);
        generateCodes(node.right, code + "1", huffmanCodes);
    }
}
    

