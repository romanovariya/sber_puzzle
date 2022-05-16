'use strict';

document.addEventListener('DOMContentLoaded', () => {

const tabsItem = document.querySelectorAll('.tabs__item'),
    pathItem = document.querySelectorAll('.levels__item'),
    tabs = document.querySelector('.tabs');

    const resetClasses = (array, selector) => {
        array.forEach(item => {
            item.classList.remove(selector);
        })
    }
tabs.addEventListener('click', e => {
    const target = e.target;
    if (target.closest('.tabs__item')) {
        const activeContentSelector = target.getAttribute('data-target'),
            activeContent = document.querySelector(`${activeContentSelector}`);
        resetClasses(tabsItem, 'tabs__item--active');
        resetClasses(pathItem, 'levels__item--active');
        resetClasses(pathItem, 'hints__item--active');
        activeContent.classList.toggle('levels__item--active');
        target.classList.toggle('tabs__item--active');
    }
})
})