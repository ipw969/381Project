package enumerators;

/**
 * This Enumerator is used to depict what type of Transformer the TransformSpot
 * class is. This is important because the transform spot should only support
 * certain directions depending on its type.
 */
public enum TransformerType {

    DIAGONAL, // This TransformerType will support both horizontal and verticle motion.
    HORIZONTAL, // This TransformerType will only support horizontal movmenet (along the x-axis)
    VERTICAL        // This TransformerType will only support vertical movement (along the y-axis).
}
