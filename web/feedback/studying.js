/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
function showDetails(type) {
    let title = "";
    let content = "";
    switch (type) {
        case 'stu1':
            title = "Becoming a top creator on EasyQuiz";
            content = `
<p>Top creators on EasyQuiz are determined by criteria like the number of flashcard sets you create and the number of viewers each set receives as compared to the activity of other users. Because many people create flashcard sets on EasyQuiz every day, requirements to be a top creator can change over time.</p>
<br/>
<p>Thank you to all the top creators out there for making EasyQuiz a vibrant place to study!</p>
            `;
            break;
        case 'stu2':
            title = "Organizing study content with folders";
            content = `
<p>You can add all kinds of study content to folders to help you stay organized.</p>
<br/>
<p><strong>To create a folder:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Select <strong>Create</strong> at the top of the page.</li>
  <li>3. Select <strong>Folder</strong>.</li>
  <li>4. Enter a title.</li>
  <li>5. Select <strong>Create folder</strong>.</li>
</ol>
<br/>
<p><strong>To add study content to a folder:</strong></p>
<ol>
  <li>1. Open the study content you'd like to add to a folder.</li>
  <li>2. Select <strong>Save</strong>.</li>
  <li>3. Select <strong>Add to folder</strong> and the desired folder.</li>
</ol>
<br/>
<p><strong>Settings and options:</strong></p>
<ul>
  <li>1. Folders you create are visible to others unless all the sets in it are private.</li>
  <li>2. Subfolders are currently unavailable.</li>
  <li>3. Add folders to classes you own to keep study materials organized and easy to find.</li>
</ul>
`;
            break;
        case 'stu3':
            title = "What are flashcard sets?";
            content = `
            <p>A flashcard set is a set of questions paired with their matching answers. You can study this kind of content using EasyQuiz's activities.<br/>
<br/>
Flashcard sets can include questions and answers.<br/>
<br/>
There are millions of flashcard sets you can search for and study with EasyQuiz. You can also <a href="#">create your own flashcard sets</a> and <a href="#">share them with others</a>.</p>
`;
            break;
        case 'stu4':
            title = "Creating quiz sets";
            content = `
            <p>Creating your own quiz set lets you focus on exactly what you want to learn.</p>
<br/>
<p><strong>To create a quiz set:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Select <strong>Create</strong>.</li>
  <li>3. Select <strong>Quiz set</strong>.</li>
  <li>4. Enter a title for your set.</li>
  <li>5. Add a question in the first column.</li>
  <li>6. Add an answer in the second column.</li>
  <li>7. Select <strong>Create</strong> to save and publish your set.</li>
</ol>
<br/>
<p>Your work is automatically saved while you're creating and editing sets. If you don't publish your set, you can edit an autosaved draft to keep working on it.</p>
`;
            break;
        case 'stu5':
            title = "Copying and editing a set";
            content = `
            <p>You can modify someone else's set or make a copy of one of your own to make sure you're studying everything you need.</p>
<br/>
<p><strong>To copy and customize a set:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Open the set.</li>
  <li>3. Select <strong>Copy and edit</strong>.</li>
  <li>4. Make your changes and click <strong>Create</strong> to save.</li>
</ol>
`;
            break;
        case 'stu6':
            title = "Editing sets";
            content = `
            <p>You can easily edit your flashcard sets. You can also copy someone else's flashcard set and then edit your version.</p>
<br/>
<p><strong>To edit your set:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Open the set.</li>
  <li>3. Select <strong>Edit</strong>.</li>
  <li>4. Make your changes and select <strong>Done</strong>.</li>
</ol>
<br/>
<p><strong>To delete terms and definitions:</strong></p>
<ol>
  <li>Select the <strong>Trash can</strong> icon on the row you'd like to delete.</li>
</ol>
`;
            break;
        case 'stu7':
            title = "Deleting a set";
            content = `
            <p><strong>To delete a set:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Go to the set.</li>
  <li>3. Select <strong>More</strong> menu.</li>
  <li>4. Select <strong>Delete</strong>.</li>
</ol>
<p>Once you delete a set, it can't be recovered :(</p>
<br/>
<p><strong>Settings and options:</strong></p>
<ul>
  <li>You can only delete sets that you've created. You can remove sets created by others that appear in your Recent activity feed.</li>
</ul>
`;
            break;
        case 'stu8':
            title = "Finding quiz sets";
            content = `
            <p><strong>To find a quiz set:</strong></p>
<ol>
  <li>1. Go to EasyQuiz web page.</li>
  <li>2. Select <strong>Search</strong>.</li>
  <li>3. Type in the topic you're learning.</li>
  <li>4. Press <strong>Enter</strong>.</li>
</ol>
<br/>
<p><strong>Settings and options:</strong></p>
<ul>
  <li>If you're looking for a specific set, the best way to find it is to search for the author's username. After you enter their username in the search bar, you can select <strong>Users</strong> to filter your results by username.</li>
</ul>
`;
            break;
        case 'stu9':
            title = "Sharing sets and folders";
            content = `
            <p>Once you've created a quiz set or folder, or if you've found helpful content someone else has made, you can share it with your friends.</p>
<br/>
<p><strong>To share a flashcard set or folder:</strong></p>
<ol>
  <li>1. Open the set or folder.</li>
  <li>2. Select <strong>Share</strong>.</li>
</ol>
`;
            break;
        case 'stu10':
            title = "Studying with Flashcards";
            content = `
            <p>Test your knowledge with Flashcards as you review your questions to work toward gaining mastery.</p>
<br/>
<p><strong>To study with Flashcards:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Open a set.</li>
  <li>3. Select <strong>Flashcards</strong>.</li>
  <li>4. Click anywhere on the card to flip it over.</li>
  <li>5. Use the arrow buttons on your keyboard or click the arrows below the card to go forward or back through your set.</li>
</ol>
`;
            break;
        case 'stu11':
            title = "Studying with Test";
            content = `
          <p>Test gives you the chance to see how you'll perform on an exam.</p>
<br/>
<p><strong>To study with Test:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Open a set.</li>
  <li>3. Select <strong>Test</strong>.</li>
  <li>4. Set the number and types of questions you'd like to include.</li>
  <li>5. Select <strong>Start test</strong>.</li>
  <li>6. Take your test.</li>
  <li>7. Select <strong>Submit test</strong> to see your score and review your test.</li>
</ol>  
`;
            break;
    }
    document.getElementById('detailTitle').textContent = title;
    document.getElementById('detailContent').innerHTML = content;
    document.getElementById('detailView').classList.remove('hidden');
}
function hideDetails() {
    document.getElementById('detailView').classList.add('hidden');
}






