#381Project
##Data Structure Viewer
An application for the designing and illustration of common computer science data structures in a visual manner.

## Team Members:
- Anja Gilje
- Ryan La Forge
- Iain Workman

## System Requirements:
JRE v1.8 (Available from https://www.java.com/en/download/)

## Running the Application:
From the command line type:
`java -jar DataStructureViewer.jar `

*NOTE1: This assumes that java is available in the $PATH envvar*
*NOTE2: This assumes that the version of java within the $PATH envvar is v1.8+ (to check this type `java -version`)*

## The Source Code:
The source code can be browsed at:

https://github.com/iainworkman/381Project

or cloned locally using:

git clone https://github.com/iainworkman/381Project.git

additionally JavaDocs are available to browse at:

http://iainworkman.github.io/381Project/

##Features:

### Drag 'n' Drop Toolbar: 
Click and hold the primary mouse button over the element in the left hand too bar, then drag out into the workspace to the point you wish to add the element and release the mouse button.

### Single Selection: 
Click the primary mouse button on the element you wish to select. This will deselect all currently selected elements and select the one clicked on

### Multi-Selection with Keyboard Modifier: 
Hold down the shift key and click on multiple items in the workspace. Each additional item clicked will be added to the current selection

### Multi-Selection with Rubber Band: 
Click on the workspace and drag a rectangle to encompass the items you wish to select. Holding down shift while performing this action will append the items within the rectangle to the current selection, otherwise the selection will be cleared and only the items within the rectangle will be selected.

### Clearing Selection: 
Click the secondary mouse button in an empty section of the workspace to deselect all items

### Editing Values within Elements:
Hover over the elements within the workspace whose value you wish to alter until the standard text edit cursor is displayed. Keyboard input will not edit the data value within the element

### Translation:
Select the element(s) you wish to be translated, then press and hold the primary mouse button within an element and move the mouse. This will translate all the selected elements.

### Transformation (resize):
Select the element you wish to resize. Blue squares will appear in each corner to indicate the resize action points. Click the appropriate resize action point and then drag the mouse outwards to increase the size of the element, or inwards to decrease the size of the element. NOTE: All elements have a minimum size which they will not resize below. This is usually their starting size.

### Z-Index Altering:
Click the secondary mouse button over the element whose Z-Index you wish to alter, then selected either "Bring to Front" or "Send to Back" to alter the element's Z-Index from the context menu which appears. The Z-Index is used in determining which item is clicked in the case of overlapping elements.

### Deleting Elements (Context Menu):
Click the secondary mouse button over an element in the workspace, then select "Delete Element" from the context menu which appears in order to delete the element. NOTE: All connected paths to the WorkSpaceElement will be deleted along with the element

### Deleting Elements (Swipe):
Click and hold the primary mouse button over an element, and then quickly swipe the element in any direction, releasing the mouse button at the end of the swipe. The element will then be deleted.

## Beginning a Path Draw: 
Click the primary mouse button over a blue (outgoing) hotspot in order to start path drawing

### Completing a Path Draw:
Click the primary mouse button over a red (incoming) hotspot in order to end path drawing

### Cancel a Path Draw:
Click the secondary mouse button while in the process of path drawing in order to cancel that path.

