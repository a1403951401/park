import getCookie from '@/utils/cookies';
import request from '@/utils/request';
import {ElMessage} from 'element-plus';

export class User {
    name: string;
    is_admin: boolean;

    constructor(name: string, is_admin: string) {
        this.name = name;
        this.is_admin = is_admin === "true";
    }

    login(username: string, password: string, code: string): Promise<boolean> {
        return request
            .post('/api/auth/login', {
                username: username,
                password: password,
                code: code,
            })
            .then(response => {
                if (response['code'] === 200 && response['message'] === "ok") {
                    return true;
                } else {
                    ElMessage.error(response['message']);
                }
            })
            .catch(() => {
                return false;
            });
    }

    register(username: string, password: string, code: string, name: string, license_id: string, phone: string): Promise<boolean> {
        return request
            .post('/api/auth/register', {
                username: username,
                password: password,
                name: name,
                license_id: license_id,
                phone: phone,
                code: code,
            })
            .then(response => {
                if (response['code'] === 200 && response['message'] === "ok") {
                    return true;
                } else {
                    ElMessage.error(response['message']);
                }
            })
            .catch(() => {
                return false;
            });
    }

    logout(): Promise<boolean> {
        return request.get('/api/auth/logout', {}).then(() => {
            return true;
        });
    }
}

export function getUser(): User {
    return new User(getCookie('name'), getCookie('is_admin'));
}

export default getUser();
