import request from '@/utils/request';
import dayjs from 'dayjs';


export function getUserData(
    username?: string,
    name?: string,
    license_id?: string,
    is_admin?: string,
    phone?: string,
    pageSize?: number,
    page?: number
): Promise<Array<Map<string, string>>> {
    return request.get('/api/user/', {
        username: username ? username : "",
        name: name ? name : "",
        license_id: license_id ? license_id : "",
        is_admin: is_admin ? is_admin : "",
        phone: phone ? phone : "",
        page_size: pageSize ? pageSize : 50,
        current_page: page ? page - 1 : 0,
    }).then(response => {
        return response.data;
    });
}
export function deleteUser(id: number): Promise<boolean> {
    return request.delete('/api/user/' + id, {}).then(response => {
        return response['code'] === 200;
    });
}