import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import {getUser} from '@/utils/user';

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Index',
        // redirect: '/card',
        meta: {
            title: '首页',
        },
        children: [
            {
                path: 'card',
                name: 'card',
                meta: {
                    title: '车辆管理',
                },
                component: () => import('@/components/card.vue'),
            },
        ],
        component: () => import('../views/Index.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

// 登录重定向
router.beforeEach(function (to, from, next) {
    // 修改标题
    if (to.meta.title) {
        document.title = to.meta.title + ' - Park';
    }
    const user = getUser();
    if (user.name === '' && to.path !== '/login') {
        next({path: '/login'});
    } else {
        next();
    }
});

export default router;

