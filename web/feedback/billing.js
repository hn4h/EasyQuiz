/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
function showDetails(type) {
    let title = "";
    let content = "";
    switch (type) {
        case 'premium':
            title = "Buy a premium package";
            content = `
                <p><strong>To buy a premium package:</strong></p>
<ol>
  <li>1. Log in to your account.</li>
  <li>2. Click the "Upgrade" button on the head of the page.</li>
  <li>3. Choose a package you want to buy.</li>
  <li>4. This page redirects you to a payment page.</li>
  <li>5. Make payment.</li>
</ol>
            `;
            break;
        case 'ends':
            title = "What happens if my paid subscription ends?";
            content = `
<p>If your paid subscription ends, your account will revert to the free version when your upgrade expires.</p>
<br/>
<p>When your account returns to the free version, you'll no longer have access to any upgraded features.</p>
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




