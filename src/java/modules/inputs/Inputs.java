package src.java.modules.inputs;

import java.util.HashMap;

/**
 * Module of Inputs with the predefined enum and its getter and setter.
 */
public class Inputs {
    private static final HashMap<Type, IInputs[]> inputs = new HashMap<Type, IInputs[]>() {{
        put(Type.ACTION, BattleActionInputs.values());
        put(Type.CONFIRMATION, ConfirmationInputs.values());
        put(Type.DIRECTION, DirectionInputs.values());
        put(Type.GLOBAL, GlobalInputs.values());
        put(Type.EQUIP, EquipInputs.values());
        put(Type.TRADE, TradeConfirmationInputs.values());
    }};

    public static IInputs[] get(Type key) {
        return inputs.get(key);
    }

    public enum Type {ACTION, CONFIRMATION, DIRECTION, GLOBAL, EQUIP, TRADE}
}
