package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;

/**
 * ModuleCard class represents a module card which contains the details of
 * the module card.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";
    @FXML
    private Label id;

    @FXML
    private Label moduleCode;

    @FXML
    private Label moduleName;

    @FXML
    private Label moduleCredit;

    /**
     * The constructor of ModuleCard. Sets the id and module
     * code fields with their values.
     *
     * @param module The module whose module code is being set.
     * @param position The position of the module in the module list.
     */
    public ModuleCard(Module module, int position) {
        super(FXML);
        id.setText(position + ". ");
        moduleCode.setText(module.getModuleCode().moduleCode);
        moduleName.setText("Name: " + module.getModuleName().moduleName);
        moduleCredit.setText("Module Credit: " + String.valueOf(module.getModuleCredit().moduleCredit));
    }
}
