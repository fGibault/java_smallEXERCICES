import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class bloodType {

    public static List<String> computeBloodGenes(String parent1, String parent2) {
        // Carte pour contenir les génotypes possibles pour chaque phénotype de groupe
        // sanguin
        Map<String, List<String>> bloodTypeGenes = new HashMap<>();
        bloodTypeGenes.put("A", Arrays.asList("A", "O"));
        bloodTypeGenes.put("B", Arrays.asList("B", "O"));
        bloodTypeGenes.put("AB", Arrays.asList("A", "B"));
        bloodTypeGenes.put("O", Arrays.asList("O"));

        // Obtenir les gènes possibles pour chaque parent
        List<String> parent1Genes = bloodTypeGenes.get(parent1);
        List<String> parent2Genes = bloodTypeGenes.get(parent2);

        // Calculer la combinaison des gènes des deux parents
        List<List<String>> possibleChildGenes = new ArrayList<>();
        for (String gene1 : parent1Genes) {
            for (String gene2 : parent2Genes) {
                possibleChildGenes.add(Arrays.asList(gene1, gene2));
            }
        }

        // Mapper les paires de gènes aux types sanguins et supprimer les doublons
        List<String> uniqueChildBloodTypes = possibleChildGenes.stream()
                .map(bloodType::mapGenesToBloodType)
                .distinct()
                .collect(Collectors.toList());

        return uniqueChildBloodTypes;
    }

    private static String mapGenesToBloodType(List<String> genes) {
        String gene1 = genes.get(0);
        String gene2 = genes.get(1);

        if (gene1.equals("A") && gene2.equals("O") || gene1.equals("O") && gene2.equals("A")) {
            return "A";
        } else if (gene1.equals("B") && gene2.equals("O") || gene1.equals("O") && gene2.equals("B")) {
            return "B";
        } else if (gene1.equals("A") && gene2.equals("B") || gene1.equals("B") && gene2.equals("A")) {
            return "AB";
        } else if (gene1.equals("A") && gene2.equals("A")) {
            return "A";
        } else if (gene1.equals("B") && gene2.equals("B")) {
            return "B";
        } else if (gene1.equals("O") && gene2.equals("O")) {
            return "O";
        }
        // Solution de repli en cas de combinaison inattendue
        return "Inconnu";
    }

    public static void main(String[] args) {
        List<String> childBloodTypes = computeBloodGenes("A", "B");
        // Afficher le résultat
        for (String bloodType : childBloodTypes) {
            System.out.println(bloodType);
        }
    }
}
