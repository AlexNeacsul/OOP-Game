package Enumerations;

public enum CellCommands {
    ENEMY(new String[]{"Attack", "Concede"}),
    ENEMY_DEFEATED(new String[]{"Move"}),
    SANCTUARY(new String[]{"Bless"}),
    SANCTUARY_VISITED(new String[]{"Move"}),
    VOID(new String[]{"Move"}),
    PORTAL(new String[]{"Ascend"}),
    FIRST_MOVE(new String[]{"Move"}),
    MOVE(new String[]{"North", "South", "East", "West"}),
    ATTACK(new String[]{"Strike", "Spell"});


    private final String[] commands;

    CellCommands(String[] commands) {
        this.commands = commands;
    }

    public boolean isValidCommand(String command) {
        for (String c : commands) {
            if (c.equals(command)) {
                return true;
            }
        }
        return false;
    }

    public String getOptions() {
        StringBuilder options = new StringBuilder();
        int i = 1;
        for (String command : commands) {
            options.append(i).append(". ");
            switch (command) {
                case "Attack":
                    options.append("Attack");
                    break;
                case "Concede":
                    options.append("Concede");
                    break;
                case "Bless":
                    options.append("Bless");
                    break;
                case "Ascend":
                    options.append("Ascend");
                    break;
                case "Move":
                    options.append("Move");
                    break;
                case "North":
                    options.append("North");
                    break;
                case "South":
                    options.append("South");
                    break;
                case "East":
                    options.append("East");
                    break;
                case "West":
                    options.append("West");
                    break;
                case "Strike":
                    options.append("Strike");
                    break;
                case "Spell":
                    options.append("Spell");
                    break;
            }
            i++;
            options.append("\n");
        }
        return options.toString();
    }
}
