

// when the page is loaded if there is researchNotes in the local storage then load it.. (in the element on the dom with id 'notes')
document.addEventListener('DOMContentLoaded', ()=>{
  chrome.storage.local.get(["researchNotes"], function (result) {
    if (result.researchNotes) {
      document.getElementById("notes").value = result.researchNotes;
    }
  });

  // listening to the summarize button and working on it..
  document
    .getElementById("summarizeBtn")
    .addEventListener("click", summarizeText);
    
  // listening to the save notes button and working on it..  
  document
    .getElementById("saveNotesBtn")
    .addEventListener("click", saveNotes);

});


// defining the 'summarizeText' function -> calling api and all..
async function summarizeText(params) {
    try {
        const [tab] = await chrome.tabs.query({active:true, currentWindow:true});// getting user's current active tab.

        // now getting the selected text from the user (current tab's)
        const [{ result }] = await chrome.scripting.executeScript({
          target: { tabId: tab.id },
          function: () => window.getSelection().toString(),
        });

        if(!result){
            showResult("Please select some text to process . . .");
            return;
        }

        // since now the user have some selected text...
        const response = await fetch(
          "http://localhost:8080/api/research/process",
          {
            method: "POST",
            headers: { "Content-type": "application/json" },
            body: JSON.stringify({ content: result, operation: "summarize" }),
          }
        );

        if (!response.ok) {
          throw new Error(`API Error: ${response.status}`);
        }

        const text = await response.text();

        // note in the response text wherever is '\n' we need to replace it by <br> to make it compatible to HTML format.
        showResult(text.replace(/\n/g, "<br>"));
        
    } catch (error) {
        showResult("Error" + error.message);
    }
} 



async function saveNotes() {
    const notes = document.getElementById('notes').value;
    chrome.storage.local.set({researchNotes:notes}, function(){
        alert('Notes saved successfully');
    });
    
}






function showResult(content){
    document.getElementById(
      "results"
    ).innerHTML = `<div class="result-item"><div class="result-content">${content}</div></div>`;
}