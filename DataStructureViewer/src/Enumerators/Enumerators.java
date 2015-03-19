/*
 * This class is responsible for holding all of the Enumerators that will be used in the project. 
 * Each enumerator serves a specific purpose, and the purpose should be detailed in each method header.
 */
package Enumerators;

/**
 *
 * @author Ryan
 */
public class Enumerators {
    
    /**This Enumerator is used to depict what type of Transformer the TransformSpot class is.
     * This is important because the transform spot should only support certain directions depending on its type.
     */
     public enum TransformerType {

        DIAGONAL,     // This TransformerType will support both horizontal and verticle motion.
        HORIZONTAL,     // This TransformerType will only support horizontal movmenet (along the x-axis)
        VERTICLE        // This TransformerType will only support verticle movement (along the y-axis).
    }
     
     /**This Enumerator is used to depict where the location of the TransformSpot should be.
      * This is important because each TransformSpot will be at a different location within a WorkSpaceViewElement.
      */
     public enum TransformerLocation {
         TOPLEFT,
         TOPRIGHT,
         BOTTOMLEFT,
         BOTTOMRIGHT,
         MIDDLERIGHT,
         MIDDLELEFT,
         MIDDLETOP,
         MIDDLEBOTTOM
     }
     
}
