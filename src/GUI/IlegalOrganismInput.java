package GUI;

public class IlegalOrganismInput extends RuntimeException {
    public IlegalOrganismInput() {
        super("Number of Carnivores, Herbivores, and Plants field(s) must contains digits only and must not be empty");
    }
}
