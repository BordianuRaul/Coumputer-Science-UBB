%sysms x
%f(x) = exp(x)
%taylor(f(x),x,'order',8)
%factorial(0:7)
%taylor(sin(x),x,'order',8)
%
%
%
%
%
%
%taylor(sin(x), x, 'order', 8)
%
% EX 1
%

%syms x %symbolic variable
%f(x) = exp(x);

%fplot(x, [-3,3]);
%clf;
%hold on
%for i = 1:4
%    T(x) = taylor(f(x), x, 'order', i+1) % T = Taylor
%    fplot(T, [-3,3])
%end


%%%%%%%%%%%%%%%%%%%%
%n = 0;

%while 3 / factorial(n+1) >= 10^-6
%    n = n + 1;
%end
%n
%T(x) = taylor(f(x), x, 'order', n+1); % order ~ degree of poly
%aprox = double(T(1));
%exact = double(exp(1));
%abs(exact - aprox)


%EX 2

% syms x % symbolic variable
% f(x) = sin(x);
% 
% 
% clf;
% hold on
% fplot(f, [-pi,pi]); % plot the original function
% 
% for i = [3:5]
%     T(x) = taylor(f(x), x, 'order', i+1); % T = Taylor series expansion
%     fplot(T, [-pi,pi]) % plot each approximation
% end
% 
% %%%%%%%%%%%%%%%%%%%%
% n = 0;
% 
% while (pi/5)^(2*n+1) / factorial(2*n+1) >= 10^-5
%     n = n + 1;
% end
% n
% T(x) = taylor(f(x), x, 'order', 2*n+1); % order ~ degree of polynomial
% aprox = double(T(pi/5)) % approximation at x = 1
% exact = double(sin(pi/5)) % exact value of e^1
% abs(exact - aprox) % absolute error

%EX 3

%sysms x
%f(x) = exp(x)
%taylor(f(x),x,'order',8)
%factorial(0:7)
%taylor(sin(x),x,'order',8)
%
%
%
%
%
%
%taylor(sin(x), x, 'order', 8)
%
% EX 1
%

%syms x %symbolic variable
%f(x) = exp(x);

%fplot(x, [-3,3]);
%clf;
%hold on
%for i = 1:4
%    T(x) = taylor(f(x), x, 'order', i+1) % T = Taylor
%    fplot(T, [-3,3])
%end


%%%%%%%%%%%%%%%%%%%%
%n = 0;

%while 3 / factorial(n+1) >= 10^-6
%    n = n + 1;
%end
%n
%T(x) = taylor(f(x), x, 'order', n+1); % order ~ degree of poly
%aprox = double(T(1));
%exact = double(exp(1));
%abs(exact - aprox)


%EX 2

% syms x % symbolic variable
% f(x) = sin(x);
% 
% 
% clf;
% hold on
% fplot(f, [-pi,pi]); % plot the original function
% 
% for i = [3:5]
%     T(x) = taylor(f(x), x, 'order', i+1); % T = Taylor series expansion
%     fplot(T, [-pi,pi]) % plot each approximation
% end

%%%%%%%%%%%%%%%%%%%%
% n = 0;
% 
% r = mod(100*pi/3, 2*pi)
% while r^(2*n+1) / factorial(2*n+1) >= 10^-5
%     n = n + 1;
% end
% n

%ex4


syms x % symbolic variable
f(x) = log(1+x);


clf;
hold on
fplot(f, [-0.9,1]); % plot the original function

for i = [2:5]
    T(x) = taylor(f(x), x, 'order', i+1); % T = Taylor series expansion
    fplot(T, [-0.9,1]) % plot each approximation
end

n = 10 ^ 5
n
%add the taylor part
