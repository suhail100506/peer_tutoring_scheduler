(function () {
    const storageKey = 'pts-theme';
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)');
    const root = document.documentElement;
    const iconForTheme = {
        light: 'bi bi-sun-fill',
        dark: 'bi bi-moon-stars-fill'
    };

    function updateToggleIcons(theme) {
        document.querySelectorAll('[data-toggle-theme] i').forEach(icon => {
            icon.className = iconForTheme[theme] || iconForTheme.light;
        });
    }

    function setTheme(theme) {
        root.setAttribute('data-theme', theme);
        localStorage.setItem(storageKey, theme);
        updateToggleIcons(theme);
    }

    function initTheme() {
        const storedTheme = localStorage.getItem(storageKey);
        if (storedTheme) {
            setTheme(storedTheme);
        } else if (prefersDark.matches) {
            setTheme('dark');
        } else {
            setTheme('light');
        }
    }

    function toggleTheme() {
        const current = root.getAttribute('data-theme') || 'light';
        const next = current === 'light' ? 'dark' : 'light';
        setTheme(next);
    }

    document.addEventListener('DOMContentLoaded', () => {
        initTheme();
        document.querySelectorAll('[data-toggle-theme]').forEach(button => {
            button.addEventListener('click', toggleTheme);
        });
    });

    prefersDark.addEventListener('change', event => {
        const storedTheme = localStorage.getItem(storageKey);
        if (!storedTheme) {
            setTheme(event.matches ? 'dark' : 'light');
        }
    });
})();
