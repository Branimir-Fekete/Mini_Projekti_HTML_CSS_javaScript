const header = document.getElementById('header');
const title = document.getElementById('title');
const excerpt = document.getElementById('excerpt');
const profile_img = document.getElementById('profile_img');
const name = document.getElementById('name');
const date = document.getElementById('date');

const animated_bgs = document.querySelectorAll('.animated-bg');
const animated_bg_texts = document.querySelectorAll('.animated-bg-text');

setTimeout(getData, 2500);

function getData() {
  header.innerHTML = `
    <img src="https://www.simplilearn.com/ice9/free_resources_article_thumb/What_is_human_computer_interaction.jpg" alt=""/>

    `;
  title.innerHTML = `
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore perferendis
    `;
  profile_img.innerHTML = `
    <img src="https://media.sproutsocial.com/uploads/2022/06/profile-picture.jpeg" alt=""/>

    `;
  name.innerHTML = 'John doe';
  date.innerHTML = 'Stu 08, 2023';

  animated_bgs.forEach((bg) => bg.classList.remove('animated-bg'));
  animated_bg_texts.forEach((bg) => bg.classList.remove('animated_bg_text'));
}
