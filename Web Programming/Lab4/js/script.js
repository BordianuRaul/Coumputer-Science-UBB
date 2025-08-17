function moveItem(sourceId, destinationId) {
  var sourceSelect = document.getElementById(sourceId);
  var destinationSelect = document.getElementById(destinationId);
  var selectedIndex = sourceSelect.selectedIndex;

  if (selectedIndex >= 0 && sourceSelect.options[selectedIndex].value === "Sterge") {
    sourceSelect.remove();
    return;
  }
  if (selectedIndex >= 0) {
    var option = sourceSelect.options[selectedIndex];
    sourceSelect.remove(selectedIndex);
    destinationSelect.add(option);
  }
}
