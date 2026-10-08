-- Выполните в Supabase: SQL Editor -> New query
create table if not exists public.workouts (
  id bigint generated always as identity primary key,
  title text not null,
  level text not null default 'Новичок',
  rounds int not null default 3,
  duration_min int not null default 30,
  description text default ''
);

create table if not exists public.training_logs (
  id bigint generated always as identity primary key,
  workout_title text not null,
  trained_on date not null default current_date,
  rounds_done int not null default 0,
  notes text default ''
);

alter table public.workouts enable row level security;
alter table public.training_logs enable row level security;

-- Учебный вариант: полный доступ для anon-ключа. Для продакшена добавьте авторизацию!
create policy "workouts_all" on public.workouts for all to anon using (true) with check (true);
create policy "logs_all" on public.training_logs for all to anon using (true) with check (true);

insert into public.workouts (title, level, rounds, duration_min, description) values
('Базовая стойка и джеб', 'Новичок', 3, 25, 'Стойка, работа ног, джеб и прямой удар'),
('Работа на мешке', 'Средний', 6, 40, 'Серии ударов, хуки и апперкоты на мешке'),
('Бой с тенью', 'Новичок', 4, 20, 'Разминка, защита и уклоны'),
('Спарринг-подготовка', 'Профи', 8, 60, 'Лапы, клинч, контратаки и выносливость'),
('Скакалка и кардио', 'Средний', 5, 30, 'Скакалка, бёрпи, пресс');
