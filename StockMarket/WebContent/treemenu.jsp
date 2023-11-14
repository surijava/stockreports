<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Parse Tree</title>
<meta name="description" content="A collapsible tree layout with all of the leaf nodes at the same layer." />
  <!-- Copyright 1998-2018 by Northwoods Software Corporation. -->
  <meta charset="UTF-8">
  <script src="../release/go.js"></script>
    <script src="../assets/js/goSamples.js"></script>  <!-- this is only for the GoJS Samples framework -->
  <script id="code">
    function init() {
      if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
      var $ = go.GraphObject.make;  // for conciseness in defining templates
      myDiagram =
        $(go.Diagram, "myDiagramDiv",
          {
            allowCopy: false,
            allowDelete: false,
            allowMove: false,
            initialContentAlignment: go.Spot.Center,
            initialAutoScale: go.Diagram.Uniform,
            layout:
              $(FlatTreeLayout,  // custom Layout, defined below
                { angle: 90,
                  compaction: go.TreeLayout.CompactionNone }),
            "undoManager.isEnabled": true
          });

      myDiagram.nodeTemplate =
        $(go.Node, "Vertical",
          { selectionObjectName: "BODY" },
          $(go.Panel, "Auto", { name: "BODY" },
            $(go.Shape, "RoundedRectangle",
              new go.Binding("fill"),
              new go.Binding("stroke")),
            $(go.TextBlock,
              { font: "bold 12pt Arial, sans-serif", margin: new go.Margin(4, 2, 2, 2) },
              new go.Binding("text"))
          ),
          $(go.Panel,  // this is underneath the "BODY"
            { height: 15 },  // always this height, even if the TreeExpanderButton is not visible
            $("TreeExpanderButton")
          )
        );

      myDiagram.linkTemplate =
        $(go.Link,
          $(go.Shape, { strokeWidth: 1.5 }));

      // set up the nodeDataArray, describing each part of the sentence
      var nodeDataArray = [
        { key: 1, text: "Group", fill: "#f68c06", stroke: "#4d90fe" },
        { key: 2, text: "TATA", fill: "#f68c06", stroke: "#4d90fe", parent: 1 },
        { key: 3, text: "BAJAJ", fill: "#ccc", stroke: "#4d90fe", parent: 1 },
        { key: 4, text: "BIRLA", fill: "#f8f8f8", stroke: "#4d90fe", parent: 1 },
        { key: 6, text: "Nelco", fill: "#ccc", stroke: "#4d90fe", parent: 2 },
        { key: 7, text: "Tata Communications Ltd", fill: "#f8f8f8", stroke: "#4d90fe", parent: 2 },
		{ key: 8, text: "Tata Chemical Ltd", fill: "#ccc", stroke: "#4d90fe", parent: 2 },
        { key: 9, text: "Tata Global Beverages Ltd", fill: "#f8f8f8", stroke: "#4d90fe", parent: 2 },
		{ key: 10, text: "TCS", fill: "#ccc", stroke: "#4d90fe", parent: 2 },
		{ key: 11, text: "TATA Power", fill: "#f8f8f8", stroke: "#4d90fe", parent: 2 },
		{ key: 12, text: "TATA Motors", fill: "#f8f8f8", stroke: "#4d90fe", parent: 2 },
		{ key: 13, text: "TATA Steel", fill: "#f8f8f8", stroke: "#4d90fe", parent: 2 },
		{ key: 14, text: "Voltas", fill: "#f8f8f8", stroke: "#4d90fe", parent: 2 },
		{ key: 15, text: "BAJAJ Finance", fill: "#f8f8f8", stroke: "#4d90fe", parent: 3 },
		{ key: 16, text: "BAJAJ Electronics", fill: "#f8f8f8", stroke: "#4d90fe", parent: 3 },
		{ key: 16, text: "Hindalco", fill: "#f8f8f8", stroke: "#4d90fe", parent: 4 },
		{ key: 17, text: "Grasim", fill: "#f8f8f8", stroke: "#4d90fe", parent: 4 },
		{ key: 18, text: "Ultra Tech Cement", fill: "#f8f8f8", stroke: "#4d90fe", parent: 4 },
        
      ]

      // create the Model with data for the tree, and assign to the Diagram
      myDiagram.model =
        $(go.TreeModel,
          { nodeDataArray: nodeDataArray });
    }

    // Customize the TreeLayout to position all of the leaf nodes at the same vertical Y position.
    function FlatTreeLayout() {
      go.TreeLayout.call(this);  // call base constructor
    }
    go.Diagram.inherit(FlatTreeLayout, go.TreeLayout);

    // This assumes the TreeLayout.angle is 90 -- growing downward
    /** @override */
    FlatTreeLayout.prototype.commitLayout = function() {
      go.TreeLayout.prototype.commitLayout.call(this);  // call base method first
      // find maximum Y position of all Nodes
      var y = -Infinity;
      this.network.vertexes.each(function(v) {
          y = Math.max(y, v.node.position.y);
        });
      // move down all leaf nodes to that Y position, but keeping their X position
      this.network.vertexes.each(function(v) {
          if (v.destinationEdges.count === 0) {
            // shift the node down to Y
            v.node.position = new go.Point(v.node.position.x, y);
            // extend the last segment vertically
            v.node.toEndSegmentLength = Math.abs(v.centerY - y);
          } else {  // restore to normal value
            v.node.toEndSegmentLength = 10;
          }
        });
    };
    // end FlatTreeLayout
  </script>
</head>
<body onload="init()">
<div id="sample">
  <div id="myDiagramDiv" style="border: solid 1px black; width:100%; height:500px"></div>
    
</div>
</body>
</html>
