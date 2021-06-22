export function getCookie(cname: string) {
    const name = cname + '=';
    const ca = document.cookie.split(';');
    let i: any;
    for (i in ca) {
        const c = ca[i].trim();
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return '';
}

export default getCookie;
